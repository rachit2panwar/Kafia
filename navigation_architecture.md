# Navigation Architecture — AI Instruction File
# Coffee Shop Android App
# Read this file before generating ANY navigation-related code.

---

## 1. Navigation Library

This project uses **Jetpack Navigation 3** (androidx.navigation 2.8.x+).
- Use `NavHost`, `composable {}`, `navArgument`, `navDeepLink` from `androidx.navigation.compose`
- Do NOT use the older `NavController.navigate(String)` with string routes directly from ViewModels
- ViewModels must NEVER hold a `NavController` reference
- Navigation events flow from ViewModel → UI via `UiEvent` (one-shot `SharedFlow`) → Composable calls `navController.navigate(...)`

---

## 2. Why a Separate `core-navigation` Module

Without a dedicated navigation module:
- Feature modules that need to trigger navigation to another screen (e.g. `feature-home`
  navigating to `feature-detail`) would have to depend on `app/` — creating a
  **circular dependency** (app → feature → app).
- Deep link URI patterns end up scattered across manifests, nav graphs, and feature code.
- Isolated feature screen testing becomes impossible because routes can't be resolved
  without pulling in the entire app module.

**Solution:** a single `core-navigation` module that owns all route definitions and
deep link URI constants. Every feature and the app module depend on it. No feature
ever depends on another feature or on `app/`.

---

## 3. Module to Create

Add this module to the project:

```
core/
└── core-navigation/
    ├── build.gradle.kts
    └── src/
        └── main/
            └── java/
                └── com/coffeeshop/core/navigation/
                    ├── Screen.kt
                    ├── DeepLinks.kt
                    └── NavExtensions.kt
```

### 3.1 `build.gradle.kts` for `core-navigation`

```kotlin
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.coffeeshop.core.navigation"
    compileSdk = 35

    defaultConfig {
        minSdk = 26
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // Navigation 3 — routes only, no Compose UI dependency needed here
    implementation(libs.compose.navigation)
}
```

Also register it in `settings.gradle.kts`:

```kotlin
include(":core:core-navigation")
```

---

## 4. Route Definitions — `Screen.kt`

```kotlin
package com.coffeeshop.core.navigation

/**
 * Single source of truth for all navigation destinations.
 * All feature modules and app/ reference ONLY this file for routes.
 * Never hardcode route strings anywhere else in the project.
 */
sealed class Screen(val route: String) {

    // ── Auth flow ──────────────────────────────────────────────────────────
    data object Splash : Screen("splash")
    data object Login  : Screen("login")

    // ── Bottom nav tabs ────────────────────────────────────────────────────
    data object Home        : Screen("home")
    data object Favourites  : Screen("favourites")
    data object Cart        : Screen("cart")
    data object Profile     : Screen("profile")

    // ── Nested / secondary screens ─────────────────────────────────────────
    data object Orders : Screen("orders")   // reached from Profile tab

    // ── Screens with arguments ─────────────────────────────────────────────

    /**
     * Product detail screen.
     * Usage (navigating):   navController.navigate(Screen.ProductDetail.createRoute(productId))
     * Usage (destination):  route = Screen.ProductDetail.ROUTE
     * Argument extraction:  backStackEntry.arguments?.getString(Screen.ProductDetail.ARG)
     */
    data object ProductDetail : Screen("product_detail/{productId}") {
        const val ROUTE = "product_detail/{productId}"
        const val ARG   = "productId"
        fun createRoute(productId: String) = "product_detail/$productId"
    }

    /**
     * Order detail screen (from order history).
     */
    data object OrderDetail : Screen("order_detail/{orderId}") {
        const val ROUTE = "order_detail/{orderId}"
        const val ARG   = "orderId"
        fun createRoute(orderId: String) = "order_detail/$orderId"
    }
}
```

---

## 5. Deep Link URI Constants — `DeepLinks.kt`

```kotlin
package com.coffeeshop.core.navigation

/**
 * All deep link URI patterns in one place.
 *
 * AndroidManifest.xml uses the BASE scheme/host.
 * AppNavGraph.kt uses the individual patterns via navDeepLink { uriPattern = ... }.
 *
 * External deep link examples:
 *   coffeeshop://app/product/abc123      → opens ProductDetailScreen
 *   coffeeshop://app/order/xyz789        → opens OrderDetailScreen
 *   coffeeshop://app/cart                → opens CartScreen
 */
object DeepLinks {
    private const val SCHEME = "coffeeshop"
    private const val HOST   = "app"
    const val BASE           = "$SCHEME://$HOST"

    // Deep link patterns — use these in navDeepLink { uriPattern = ... }
    const val PRODUCT_DETAIL = "$BASE/product/{${Screen.ProductDetail.ARG}}"
    const val ORDER_DETAIL   = "$BASE/order/{${Screen.OrderDetail.ARG}}"
    const val CART           = "$BASE/cart"
    const val HOME           = "$BASE/home"
}
```

---

## 6. Navigation Helper Extensions — `NavExtensions.kt`

```kotlin
package com.coffeeshop.core.navigation

import androidx.navigation.NavController

/**
 * Navigates to a screen and clears the back stack up to (and including)
 * the given route. Useful for post-login navigation so the user
 * cannot press Back to return to the Login screen.
 */
fun NavController.navigateAndClearBackStack(
    route: String,
    clearUpTo: String = Screen.Splash.route
) {
    navigate(route) {
        popUpTo(clearUpTo) { inclusive = true }
        launchSingleTop = true
    }
}

/**
 * Bottom nav tab navigation — avoids duplicate back stack entries
 * and restores scroll/state when re-selecting a tab.
 */
fun NavController.navigateToTab(route: String) {
    navigate(route) {
        popUpTo(Screen.Home.route) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
```

---

## 7. Module Dependency Graph

```
         ┌─────────────────────────────┐
         │           app               │  ← assembles everything
         │  AppNavGraph, MainActivity  │
         └────────────┬────────────────┘
                      │ depends on
          ┌───────────┼────────────────────────────────┐
          ▼           ▼                                 ▼
   feature-auth   feature-home   feature-detail   feature-cart
   feature-profile feature-orders feature-favourites
          │           │
          └─────┬─────┘
                ▼
        core-navigation   ← Screen.kt, DeepLinks.kt (shared contract)
                │
        (no further deps needed for routes — pure Kotlin)


core-ui ◄── all feature modules
core-domain ◄── all feature modules
core-network ◄── feature modules that make API calls
core-data ◄── feature modules that need Room / DataStore
```

**Rules (enforce strictly):**
- No feature module imports another feature module
- No feature module imports `app`
- `core-navigation` imports NO other core module (keeps it lightweight)
- `app` is the only module that imports ALL feature modules

---

## 8. `AppNavGraph.kt` — Full Implementation

Location: `app/src/main/java/com/coffeeshop/app/navigation/AppNavGraph.kt`

```kotlin
package com.coffeeshop.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.coffeeshop.core.navigation.DeepLinks
import com.coffeeshop.core.navigation.Screen
import com.coffeeshop.core.navigation.navigateAndClearBackStack
import com.coffeeshop.feature.auth.presentation.LoginScreen
import com.coffeeshop.feature.auth.presentation.SplashScreen
import com.coffeeshop.feature.cart.presentation.CartScreen
import com.coffeeshop.feature.detail.presentation.ProductDetailScreen
import com.coffeeshop.feature.favourites.presentation.FavouritesScreen
import com.coffeeshop.feature.home.presentation.HomeScreen
import com.coffeeshop.feature.orders.presentation.OrderDetailScreen
import com.coffeeshop.feature.orders.presentation.OrdersScreen
import com.coffeeshop.feature.profile.presentation.ProfileScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        // ── Splash ───────────────────────────────────────────────────────
        composable(route = Screen.Splash.route) {
            SplashScreen(
                onNavigateToHome = {
                    navController.navigateAndClearBackStack(Screen.Home.route)
                },
                onNavigateToLogin = {
                    navController.navigateAndClearBackStack(Screen.Login.route)
                }
            )
        }

        // ── Login ────────────────────────────────────────────────────────
        composable(route = Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigateAndClearBackStack(
                        route   = Screen.Home.route,
                        clearUpTo = Screen.Login.route
                    )
                }
            )
        }

        // ── Home (bottom nav root) ───────────────────────────────────────
        composable(
            route = Screen.Home.route,
            deepLinks = listOf(navDeepLink { uriPattern = DeepLinks.HOME })
        ) {
            HomeScreen(
                onProductClick = { productId ->
                    navController.navigate(Screen.ProductDetail.createRoute(productId))
                }
            )
        }

        // ── Product Detail ───────────────────────────────────────────────
        composable(
            route = Screen.ProductDetail.ROUTE,
            arguments = listOf(
                navArgument(Screen.ProductDetail.ARG) { type = NavType.StringType }
            ),
            deepLinks = listOf(navDeepLink { uriPattern = DeepLinks.PRODUCT_DETAIL })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments
                ?.getString(Screen.ProductDetail.ARG) ?: return@composable
            ProductDetailScreen(
                productId = productId,
                onNavigateBack = { navController.popBackStack() },
                onAddToCartSuccess = { navController.navigate(Screen.Cart.route) }
            )
        }

        // ── Cart ─────────────────────────────────────────────────────────
        composable(
            route = Screen.Cart.route,
            deepLinks = listOf(navDeepLink { uriPattern = DeepLinks.CART })
        ) {
            CartScreen(
                onNavigateBack = { navController.popBackStack() },
                onOrderSuccess = {
                    navController.navigate(Screen.Orders.route) {
                        popUpTo(Screen.Cart.route) { inclusive = true }
                    }
                }
            )
        }

        // ── Favourites ───────────────────────────────────────────────────
        composable(route = Screen.Favourites.route) {
            FavouritesScreen(
                onProductClick = { productId ->
                    navController.navigate(Screen.ProductDetail.createRoute(productId))
                }
            )
        }

        // ── Profile ──────────────────────────────────────────────────────
        composable(route = Screen.Profile.route) {
            ProfileScreen(
                onNavigateToOrders = { navController.navigate(Screen.Orders.route) },
                onNavigateToFavourites = { navController.navigate(Screen.Favourites.route) },
                onLogout = {
                    navController.navigateAndClearBackStack(
                        route     = Screen.Login.route,
                        clearUpTo = Screen.Home.route
                    )
                }
            )
        }

        // ── Orders ───────────────────────────────────────────────────────
        composable(route = Screen.Orders.route) {
            OrdersScreen(
                onOrderClick = { orderId ->
                    navController.navigate(Screen.OrderDetail.createRoute(orderId))
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // ── Order Detail ─────────────────────────────────────────────────
        composable(
            route = Screen.OrderDetail.ROUTE,
            arguments = listOf(
                navArgument(Screen.OrderDetail.ARG) { type = NavType.StringType }
            ),
            deepLinks = listOf(navDeepLink { uriPattern = DeepLinks.ORDER_DETAIL })
        ) { backStackEntry ->
            val orderId = backStackEntry.arguments
                ?.getString(Screen.OrderDetail.ARG) ?: return@composable
            OrderDetailScreen(
                orderId = orderId,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
```

---

## 9. Bottom Navigation Bar — `BottomNavBar.kt`

Location: `app/src/main/java/com/coffeeshop/app/navigation/BottomNavBar.kt`

```kotlin
package com.coffeeshop.app.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.coffeeshop.core.navigation.Screen
import com.coffeeshop.core.navigation.navigateToTab

data class BottomNavItem(
    val label: String,
    val route: String,
    val iconRes: Int   // reference your drawable resource IDs here
)

val bottomNavItems = listOf(
    BottomNavItem("Home",       Screen.Home.route,       R.drawable.ic_home),
    BottomNavItem("Favourites", Screen.Favourites.route, R.drawable.ic_favourite),
    BottomNavItem("Cart",       Screen.Cart.route,       R.drawable.ic_cart),
    BottomNavItem("Profile",    Screen.Profile.route,    R.drawable.ic_profile)
)

// Screens that should SHOW the bottom nav bar
val bottomNavRoutes = setOf(
    Screen.Home.route,
    Screen.Favourites.route,
    Screen.Cart.route,
    Screen.Profile.route
)

@Composable
fun BottomNavBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected  = currentRoute == item.route,
                onClick   = { navController.navigateToTab(item.route) },
                icon      = { Icon(painter = painterResource(item.iconRes), contentDescription = item.label) },
                label     = { Text(item.label) }
            )
        }
    }
}
```

---

## 10. `MainActivity.kt` — Wiring It All Together

```kotlin
package com.coffeeshop.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.coffeeshop.app.navigation.AppNavGraph
import com.coffeeshop.app.navigation.BottomNavBar
import com.coffeeshop.app.navigation.bottomNavRoutes
import com.coffeeshop.core.ui.theme.CoffeeShopTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoffeeShopTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                Scaffold(
                    bottomBar = {
                        // Only show bottom nav on top-level tab screens
                        if (currentRoute in bottomNavRoutes) {
                            BottomNavBar(navController = navController)
                        }
                    }
                ) { innerPadding ->
                    AppNavGraph(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)  // add modifier param to AppNavGraph
                    )
                }
            }
        }
    }
}
```

---

## 11. AndroidManifest.xml — Deep Link Intent Filter

In `app/src/main/AndroidManifest.xml`, add inside `<activity>`:

```xml
<activity
    android:name=".MainActivity"
    android:exported="true"
    android:launchMode="singleTop">

    <!-- Normal launcher intent -->
    <intent-filter>
        <action android:name="android.intent.action.MAIN"/>
        <category android:name="android.intent.category.LAUNCHER"/>
    </intent-filter>

    <!-- Deep link intent filter — handles coffeeshop://app/* -->
    <intent-filter android:autoVerify="true">
        <action android:name="android.intent.action.VIEW"/>
        <category android:name="android.intent.category.DEFAULT"/>
        <category android:name="android.intent.category.BROWSABLE"/>
        <data
            android:scheme="coffeeshop"
            android:host="app"/>
    </intent-filter>

</activity>
```

> `android:autoVerify="true"` enables Android App Links (HTTPS deep links).
> When you're ready for HTTPS deep links, also add:
> `<data android:scheme="https" android:host="coffeeshop.yourdomain.com"/>`
> and host a `/.well-known/assetlinks.json` on your server.

---

## 12. How Navigation Events Flow From ViewModel

ViewModels must NEVER hold a NavController. Use a one-shot SharedFlow:

```kotlin
// In any ViewModel (e.g. LoginViewModel)
sealed class LoginUiEvent {
    data object NavigateToHome  : LoginUiEvent()
    data class ShowError(val message: String) : LoginUiEvent()
}

@HiltViewModel
class LoginViewModel @Inject constructor(...) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<LoginUiEvent>()
    val uiEvent: SharedFlow<LoginUiEvent> = _uiEvent.asSharedFlow()

    fun onLoginClick(email: String, password: String) {
        viewModelScope.launch {
            // ... call use case ...
            _uiEvent.emit(LoginUiEvent.NavigateToHome)
        }
    }
}

// In LoginScreen composable:
@Composable
fun LoginScreen(onLoginSuccess: () -> Unit, viewModel: LoginViewModel = hiltViewModel()) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is LoginUiEvent.NavigateToHome  -> onLoginSuccess()   // nav callback from AppNavGraph
                is LoginUiEvent.ShowError       -> { /* show snackbar */ }
            }
        }
    }
    // ... UI ...
}
```

This keeps every feature composable fully decoupled from `NavController` —
the actual `navController.navigate(...)` call only ever lives in `AppNavGraph.kt`.

---

## 13. Adding a New Screen in Future (checklist)

When you add a new screen later, follow this order every time:

- [ ] 1. Add a new `data object` or `data class` to `Screen.kt` in `core-navigation`
- [ ] 2. If it has a deep link, add its URI pattern to `DeepLinks.kt`
- [ ] 3. Add a `composable { }` block to `AppNavGraph.kt` in `app/`
- [ ] 4. Update `AndroidManifest.xml` only if you're adding a new scheme/host (rare)
- [ ] 5. Create the feature's Composable screen, ViewModel, UiState, UiEvent
- [ ] 6. Wire the nav callback (lambda) from `AppNavGraph` into the Composable
- [ ] 7. Never call `navController` directly from inside the feature module

---

## 14. `settings.gradle.kts` — Full Module List (update yours to match)

```kotlin
pluginManagement { ... }
dependencyResolutionManagement { ... }

rootProject.name = "CoffeeShop"

// App
include(":app")

// Core
include(":core:core-navigation")   // ← ADD THIS
include(":core:core-ui")
include(":core:core-network")
include(":core:core-data")
include(":core:core-domain")

// Features
include(":feature:feature-auth")
include(":feature:feature-home")
include(":feature:feature-detail")
include(":feature:feature-cart")
include(":feature:feature-orders")
include(":feature:feature-profile")
include(":feature:feature-favourites")
```

---

## 15. Each Feature Module's `build.gradle.kts` — Navigation Dependency

Every feature module must add `core-navigation` as a dependency:

```kotlin
dependencies {
    implementation(project(":core:core-navigation"))
    implementation(project(":core:core-ui"))
    implementation(project(":core:core-domain"))
    // ... other deps
}
```

---

*End of navigation architecture file.
Place alongside CLAUDE.md at the project root.
Generate code in the order: core-navigation module → AppNavGraph → MainActivity → feature screens.*