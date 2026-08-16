# Coffee Shop Design System — Full Reference
# This is the detailed reference file loaded by the coffeeshop-design-system skill.
# Read this in full before generating any component or screen code.

---

## Table of Contents

1. Module structure
2. `build.gradle.kts` for `core-ui`
3. Layer 1 — Design tokens
4. Layer 2 — Theme wiring
5. Layer 3 — Component catalogue (with full signatures)
6. Decision table — core-ui vs feature module
7. Shimmer / skeleton loading system
8. Empty and error states
9. Preview system
10. `MaterialTheme` extension properties
11. Compose code conventions

---

## 1. Module Structure

```
core/core-ui/
├── build.gradle.kts
└── src/main/
    ├── java/com/coffeeshop/core/ui/
    │   ├── tokens/
    │   │   ├── Color.kt
    │   │   ├── Spacing.kt
    │   │   ├── Typography.kt
    │   │   ├── Shape.kt
    │   │   ├── Elevation.kt
    │   │   └── Motion.kt
    │   ├── theme/
    │   │   └── CoffeeShopTheme.kt
    │   ├── components/
    │   │   ├── button/
    │   │   │   ├── CoffeeButton.kt
    │   │   │   └── CoffeeIconButton.kt
    │   │   ├── card/
    │   │   │   ├── ProductCard.kt
    │   │   │   ├── ProductCardSkeleton.kt
    │   │   │   └── OrderSummaryCard.kt
    │   │   ├── input/
    │   │   │   ├── CoffeeTextField.kt
    │   │   │   └── SearchBar.kt
    │   │   ├── chip/
    │   │   │   ├── CategoryChip.kt
    │   │   │   └── SortChip.kt
    │   │   ├── badge/
    │   │   │   └── CartBadge.kt
    │   │   ├── image/
    │   │   │   └── CoffeeAsyncImage.kt
    │   │   ├── loader/
    │   │   │   ├── ShimmerBox.kt
    │   │   │   ├── ShimmerCard.kt
    │   │   │   └── FullScreenLoader.kt
    │   │   ├── state/
    │   │   │   ├── ErrorView.kt
    │   │   │   └── EmptyView.kt
    │   │   ├── topbar/
    │   │   │   └── CoffeeTopBar.kt
    │   │   └── snackbar/
    │   │       └── CoffeeSnackbarHost.kt
    │   └── preview/
    │       └── PreviewWrapper.kt
    └── res/
        └── font/
            ├── poppins_regular.ttf
            ├── poppins_medium.ttf
            ├── poppins_semibold.ttf
            └── poppins_bold.ttf
```

---

## 2. `build.gradle.kts` for `core-ui`

```kotlin
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.coffeeshop.core.ui"
    compileSdk = 35

    defaultConfig { minSdk = 26 }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }

    buildFeatures { compose = true }
}

dependencies {
    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)
    api(libs.compose.ui)                     // api so consumers get it transitively
    api(libs.compose.material3)
    api(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.coil.compose)
    // NOTE: no Hilt, no Navigation, no Retrofit here — core-ui is pure UI
}
```

---

## 3. Layer 1 — Design Tokens

### 3.1 `Color.kt`

```kotlin
package com.coffeeshop.core.ui.tokens

import androidx.compose.ui.graphics.Color

// ── Brand palette ──────────────────────────────────────────────────────────
val CoffeeBrown900  = Color(0xFF2C1700)   // darkest — text on light bg
val CoffeeBrown700  = Color(0xFF4E2C0E)   // primary — buttons, active icons
val CoffeeBrown500  = Color(0xFF7B4A22)   // secondary — pressed states
val CoffeeBrown300  = Color(0xFFD4845A)   // accent — highlights, selected chips
val CoffeeBrown100  = Color(0xFFF5DFD0)   // tint — chip backgrounds, dividers

// ── Surface / background ───────────────────────────────────────────────────
val CoffeeCream     = Color(0xFFFFF8F2)   // app background (light)
val CoffeeCard      = Color(0xFFFFFFFF)   // card surface (light)
val CoffeeCardDark  = Color(0xFF1E1208)   // card surface (dark)
val CoffeeBgDark    = Color(0xFF120C05)   // app background (dark)

// ── Semantic ───────────────────────────────────────────────────────────────
val SuccessGreen    = Color(0xFF2E7D32)
val ErrorRed        = Color(0xFFD32F2F)
val WarningAmber    = Color(0xFFF57F17)
val InfoBlue        = Color(0xFF1565C0)

// ── Neutral ────────────────────────────────────────────────────────────────
val Neutral900      = Color(0xFF1A1A1A)   // primary text
val Neutral600      = Color(0xFF6B6B6B)   // secondary text
val Neutral300      = Color(0xFFCCCCCC)   // dividers, borders
val Neutral100      = Color(0xFFF5F5F5)   // shimmer base

// ── Shimmer colours (used in ShimmerBox) ──────────────────────────────────
val ShimmerLight    = Color(0xFFE8E8E8)
val ShimmerDark     = Color(0xFF2A2A2A)
```

### 3.2 `Spacing.kt`

```kotlin
package com.coffeeshop.core.ui.tokens

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// 4dp grid system — use ONLY these values for all padding and spacing
data class Spacing(
    val xxs:  Dp =  2.dp,
    val xs:   Dp =  4.dp,
    val sm:   Dp =  8.dp,
    val md:   Dp = 12.dp,
    val lg:   Dp = 16.dp,   // default screen horizontal padding
    val xl:   Dp = 24.dp,
    val xxl:  Dp = 32.dp,
    val xxxl: Dp = 48.dp,
    val huge:  Dp = 64.dp
)
```

### 3.3 `Typography.kt`

```kotlin
package com.coffeeshop.core.ui.tokens

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.coffeeshop.core.ui.R

val PoppinsFamily = FontFamily(
    Font(R.font.poppins_regular,  FontWeight.Normal),
    Font(R.font.poppins_medium,   FontWeight.Medium),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_bold,     FontWeight.Bold)
)

val CoffeeTypography = Typography(
    // Display — app name, hero sections
    displayLarge  = TextStyle(fontFamily = PoppinsFamily, fontWeight = FontWeight.Bold,     fontSize = 32.sp, lineHeight = 40.sp),
    displayMedium = TextStyle(fontFamily = PoppinsFamily, fontWeight = FontWeight.Bold,     fontSize = 28.sp, lineHeight = 36.sp),

    // Headlines — screen titles, section headers
    headlineLarge  = TextStyle(fontFamily = PoppinsFamily, fontWeight = FontWeight.SemiBold, fontSize = 24.sp, lineHeight = 32.sp),
    headlineMedium = TextStyle(fontFamily = PoppinsFamily, fontWeight = FontWeight.SemiBold, fontSize = 20.sp, lineHeight = 28.sp),
    headlineSmall  = TextStyle(fontFamily = PoppinsFamily, fontWeight = FontWeight.SemiBold, fontSize = 18.sp, lineHeight = 24.sp),

    // Titles — card titles, list item primaries
    titleLarge  = TextStyle(fontFamily = PoppinsFamily, fontWeight = FontWeight.SemiBold, fontSize = 16.sp, lineHeight = 24.sp),
    titleMedium = TextStyle(fontFamily = PoppinsFamily, fontWeight = FontWeight.Medium,   fontSize = 14.sp, lineHeight = 20.sp),
    titleSmall  = TextStyle(fontFamily = PoppinsFamily, fontWeight = FontWeight.Medium,   fontSize = 12.sp, lineHeight = 16.sp),

    // Body — descriptions, paragraphs
    bodyLarge  = TextStyle(fontFamily = PoppinsFamily, fontWeight = FontWeight.Normal, fontSize = 16.sp, lineHeight = 24.sp),
    bodyMedium = TextStyle(fontFamily = PoppinsFamily, fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 20.sp),
    bodySmall  = TextStyle(fontFamily = PoppinsFamily, fontWeight = FontWeight.Normal, fontSize = 12.sp, lineHeight = 16.sp),

    // Labels — buttons, chips, badges
    labelLarge  = TextStyle(fontFamily = PoppinsFamily, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, lineHeight = 20.sp, letterSpacing = 0.1.sp),
    labelMedium = TextStyle(fontFamily = PoppinsFamily, fontWeight = FontWeight.Medium,   fontSize = 12.sp, lineHeight = 16.sp, letterSpacing = 0.5.sp),
    labelSmall  = TextStyle(fontFamily = PoppinsFamily, fontWeight = FontWeight.Medium,   fontSize = 10.sp, lineHeight = 14.sp, letterSpacing = 0.5.sp)
)
```

### 3.4 `Shape.kt`

```kotlin
package com.coffeeshop.core.ui.tokens

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val CoffeeShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),   // tags, small badges
    small      = RoundedCornerShape(8.dp),   // input fields, small cards
    medium     = RoundedCornerShape(12.dp),  // product cards, main cards
    large      = RoundedCornerShape(16.dp),  // bottom sheets, dialogs
    extraLarge = RoundedCornerShape(50.dp)   // buttons (pill), chips
)
```

### 3.5 `Elevation.kt`

```kotlin
package com.coffeeshop.core.ui.tokens

import androidx.compose.ui.unit.dp

object CoffeeElevation {
    val none   = 0.dp
    val low    = 2.dp    // subtle card lift
    val medium = 4.dp    // product cards
    val high   = 8.dp    // bottom sheets, FAB
    val modal  = 16.dp   // dialogs
}
```

### 3.6 `Motion.kt`

```kotlin
package com.coffeeshop.core.ui.tokens

object CoffeeMotion {
    const val durationShort  = 150   // micro-interactions (chip select)
    const val durationMedium = 300   // screen transitions, card expand
    const val durationLong   = 500   // shimmer cycle (one direction)
}
```

---

## 4. Layer 2 — Theme Wiring

### `CoffeeShopTheme.kt`

```kotlin
package com.coffeeshop.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.coffeeshop.core.ui.tokens.*

private val LightColorScheme = lightColorScheme(
    primary          = CoffeeBrown700,
    onPrimary        = CoffeeCard,
    primaryContainer = CoffeeBrown100,
    secondary        = CoffeeBrown300,
    background       = CoffeeCream,
    surface          = CoffeeCard,
    onBackground     = Neutral900,
    onSurface        = Neutral900,
    onSurfaceVariant = Neutral600,
    outline          = Neutral300,
    error            = ErrorRed
)

private val DarkColorScheme = darkColorScheme(
    primary          = CoffeeBrown300,
    onPrimary        = CoffeeBrown900,
    primaryContainer = CoffeeBrown700,
    secondary        = CoffeeBrown500,
    background       = CoffeeBgDark,
    surface          = CoffeeCardDark,
    onBackground     = CoffeeCard,
    onSurface        = CoffeeCard,
    onSurfaceVariant = Neutral300,
    outline          = Neutral600,
    error            = ErrorRed
)

// Expose spacing via CompositionLocal
val LocalSpacing = staticCompositionLocalOf { Spacing() }

@Composable
fun CoffeeShopTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    CompositionLocalProvider(LocalSpacing provides Spacing()) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography  = CoffeeTypography,
            shapes      = CoffeeShapes,
            content     = content
        )
    }
}
```

---

## 5. Layer 3 — Component Catalogue

### 5.1 `CoffeeButton.kt`

```kotlin
@Composable
fun CoffeeButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    variant: CoffeeButtonVariant = CoffeeButtonVariant.Primary
)

enum class CoffeeButtonVariant { Primary, Secondary, Ghost }

// Rules:
// Primary  → filled, CoffeeBrown700 background, white text
// Secondary → outlined, CoffeeBrown700 border and text
// Ghost    → no border, CoffeeBrown700 text only
// isLoading → replaces text with small CircularProgressIndicator (24dp)
// All variants use shape = MaterialTheme.shapes.extraLarge (pill)
```

### 5.2 `ProductCard.kt`

```kotlin
@Composable
fun ProductCard(
    name: String,
    description: String,
    price: String,
    imageUrl: String,
    isFavourite: Boolean,
    onAddToCart: () -> Unit,
    onFavouriteToggle: () -> Unit,
    modifier: Modifier = Modifier
)

// Layout:
// - Aspect ratio: wrap, image is 160dp height, fills card width
// - Card shape: MaterialTheme.shapes.medium (12dp)
// - Card elevation: CoffeeElevation.medium (4dp)
// - Favourite icon: top-right overlay on image
// - "+" button: CircleShape, CoffeeBrown700, bottom-right of card
// - Name: titleMedium
// - Description: bodySmall, color = onSurfaceVariant, maxLines = 1, overflow = Ellipsis
// - Price: titleMedium, color = primary
// - Internal padding: MaterialTheme.spacing.md (12dp)
```

### 5.3 `ProductCardSkeleton.kt`

```kotlin
@Composable
fun ProductCardSkeleton(modifier: Modifier = Modifier)

// Mirror exact layout of ProductCard but every data area is a ShimmerBox.
// Image area: ShimmerBox, 160dp height, full width
// Name area:  ShimmerBox, 16dp height, 70% width, top padding md
// Desc area:  ShimmerBox, 14dp height, 50% width, top padding xs
// Price area: ShimmerBox, 14dp height, 30% width, top padding sm
// "+" area:   ShimmerBox, 36dp circle, aligned to bottom-right
```

### 5.4 `SearchBar.kt`

```kotlin
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearchSubmit: () -> Unit,
    placeholder: String = "Search coffee, drinks...",
    modifier: Modifier = Modifier
)

// Shape: MaterialTheme.shapes.extraLarge (pill)
// Leading icon: Search icon, tint = onSurfaceVariant
// Trailing icon: visible only when query non-empty — Clear (X) icon
// Background: MaterialTheme.colorScheme.surfaceVariant
// No border/outline — surface-only look (like Swiggy search)
```

### 5.5 `CategoryChip.kt`

```kotlin
@Composable
fun CategoryChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
)

// Selected: primary background (CoffeeBrown700), white text
// Unselected: primaryContainer background (CoffeeBrown100), primary text
// Shape: MaterialTheme.shapes.extraLarge (pill)
// Text style: labelMedium
// Horizontal padding: lg (16dp), vertical: sm (8dp)
// Animation: animateColorAsState, durationShort
```

### 5.6 `SortChip.kt`

```kotlin
@Composable
fun SortChip(
    label: String,
    sortOrder: SortOrder,    // enum: DEFAULT, PRICE_ASC, PRICE_DESC, POPULAR
    currentSort: SortOrder,
    onClick: (SortOrder) -> Unit,
    modifier: Modifier = Modifier
)
// Same visual rules as CategoryChip but carries SortOrder value
```

### 5.7 `CartBadge.kt`

```kotlin
@Composable
fun CartBadge(
    count: Int,
    modifier: Modifier = Modifier
)
// Renders a red filled circle with white count text
// Visible only when count > 0
// Size: 18dp circle
// Text style: labelSmall
// Used as overlay on the Cart tab icon in BottomNavBar
```

### 5.8 `CoffeeAsyncImage.kt`

```kotlin
@Composable
fun CoffeeAsyncImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
)
// Wraps Coil AsyncImage
// Placeholder: ShimmerBox with same modifier dimensions
// Error fallback: a Box with surfaceVariant background + coffee icon centered
// Never let a broken image leave a blank white space
```

### 5.9 `CoffeeTopBar.kt`

```kotlin
@Composable
fun CoffeeTopBar(
    title: String,
    onNavigateBack: (() -> Unit)? = null,    // null = no back button shown
    actions: @Composable RowScope.() -> Unit = {},
    modifier: Modifier = Modifier
)
// Uses Material3 TopAppBar
// Back arrow shown only when onNavigateBack != null
// Background: MaterialTheme.colorScheme.surface
// Title style: headlineSmall
```

### 5.10 `CoffeeTextField.kt`

```kotlin
@Composable
fun CoffeeTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorMessage: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    trailingIcon: @Composable (() -> Unit)? = null
)
// Uses Material3 OutlinedTextField
// Shape: MaterialTheme.shapes.small (8dp)
// Error text shown below field when isError = true and errorMessage != null
```

---

## 6. Decision Table — core-ui vs Feature Module

| Component / Composable | Goes in | Reason |
|---|---|---|
| `CoffeeButton` | `core-ui` | Used on Login, Detail, Cart, Profile |
| `ProductCard` | `core-ui` | Used on Home AND Favourites |
| `ProductCardSkeleton` | `core-ui` | Accompanies ProductCard — same rule |
| `SearchBar` | `core-ui` | Could appear on Home, Orders, future |
| `CategoryChip` | `core-ui` | Home + future filter screens |
| `CartBadge` | `core-ui` | App-wide bottom nav |
| `CoffeeAsyncImage` | `core-ui` | Every screen with images |
| `ShimmerBox` / `ShimmerCard` | `core-ui` | Every screen with loading state |
| `ErrorView` | `core-ui` | Every screen with network calls |
| `EmptyView` | `core-ui` | Multiple screens (favourites, orders, search) |
| `CoffeeTopBar` | `core-ui` | Every secondary screen |
| `CoffeeTextField` | `core-ui` | Login + Profile edit |
| `HomeHeroBanner` | `feature-home` | Only on Home screen |
| `LocationBar` | `feature-home` | Only on Home screen |
| `NoDeliveryBanner` | `feature-home` | Only on Home screen |
| `OrderStatusTimeline` | `feature-orders` | Only on Order Detail |
| `OrderItemRow` | `feature-orders` | Only in Orders list/detail |
| `CartItemRow` | `feature-cart` | Only in Cart |
| `SizeSelector` | `feature-detail` | Only on Product Detail |
| `QuantityControl` | `feature-detail` | Only on Product Detail |
| `LoginSocialButton` | `feature-auth` | Only on Login |
| `ProfileMenuItem` | `feature-profile` | Only on Profile screen |
| `FavouriteButton` (standalone) | `feature-favourites` | Only in Favourites context |

**Edge case rule:** if you're unsure, ask "could a future screen in a different feature plausibly use this?" If yes → `core-ui`. If it would only ever make sense in one specific domain (orders, cart, auth) → feature module.

---

## 7. Shimmer / Skeleton Loading System

### 7.1 `ShimmerBox.kt` — base primitive

```kotlin
package com.coffeeshop.core.ui.components.loader

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.coffeeshop.core.ui.tokens.CoffeeMotion
import com.coffeeshop.core.ui.tokens.ShimmerDark
import com.coffeeshop.core.ui.tokens.ShimmerLight

@Composable
fun ShimmerBox(
    modifier: Modifier = Modifier,
    isDark: Boolean = false
) {
    val baseColor      = if (isDark) ShimmerDark  else ShimmerLight
    val highlightColor = if (isDark) Color(0xFF3A3A3A) else Color(0xFFFFFFFF)

    val shimmerColors = listOf(baseColor, highlightColor, baseColor)

    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue  = 1200f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = CoffeeMotion.durationLong * 2,
                easing         = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer_x"
    )

    val brush = Brush.linearGradient(
        colors     = shimmerColors,
        start      = Offset(translateAnim - 400f, 0f),
        end        = Offset(translateAnim, 0f)
    )

    Box(modifier = modifier.background(brush))
}
```

### 7.2 `ProductCardSkeleton.kt` — full implementation

```kotlin
@Composable
fun ProductCardSkeleton(modifier: Modifier = Modifier) {
    val spacing = MaterialTheme.spacing
    Card(
        modifier  = modifier,
        shape     = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(CoffeeElevation.medium)
    ) {
        Column {
            // Image area
            ShimmerBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            )
            Column(modifier = Modifier.padding(spacing.md)) {
                // Product name
                ShimmerBox(
                    modifier = Modifier
                        .fillMaxWidth(0.72f)
                        .height(16.dp)
                        .clip(MaterialTheme.shapes.extraSmall)
                )
                Spacer(Modifier.height(spacing.xs))
                // Description
                ShimmerBox(
                    modifier = Modifier
                        .fillMaxWidth(0.55f)
                        .height(13.dp)
                        .clip(MaterialTheme.shapes.extraSmall)
                )
                Spacer(Modifier.height(spacing.sm))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Price
                    ShimmerBox(
                        modifier = Modifier
                            .width(56.dp)
                            .height(16.dp)
                            .clip(MaterialTheme.shapes.extraSmall)
                    )
                    // "+" button circle
                    ShimmerBox(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                    )
                }
            }
        }
    }
}
```

### 7.3 Screen-level usage pattern

```kotlin
// In HomeScreen.kt
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), ...) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        if (uiState.isLoading) {
            // Show skeleton grid — same count as expected real items
            items(6) { ProductCardSkeleton(modifier = Modifier.padding(MaterialTheme.spacing.sm)) }
        } else {
            items(uiState.products, key = { it.id }) { product ->
                ProductCard(
                    name            = product.name,
                    description     = product.description,
                    price           = product.formattedPrice,
                    imageUrl        = product.imageUrl,
                    isFavourite     = product.isFavourite,
                    onAddToCart     = { viewModel.onAddToCart(product) },
                    onFavouriteToggle = { viewModel.onFavouriteToggle(product) },
                    modifier        = Modifier.padding(MaterialTheme.spacing.sm)
                )
            }
        }
    }
}
```

---

## 8. Empty and Error States

### `ErrorView.kt`

```kotlin
@Composable
fun ErrorView(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
)
// Centered column: error icon (48dp) + message (bodyMedium) + Retry CoffeeButton
// Background: transparent (inherits screen background)
// Use on every screen where a network call can fail
```

### `EmptyView.kt`

```kotlin
@Composable
fun EmptyView(
    title: String,
    subtitle: String,
    illustrationRes: Int? = null,   // optional lottie / vector resource
    actionLabel: String? = null,
    onAction: (() -> Unit)? = null,
    modifier: Modifier = Modifier
)
// Centered column: optional illustration + title (headlineSmall) + subtitle (bodyMedium)
// Optional CoffeeButton shown when actionLabel != null
// Use for: empty cart, no favourites, no order history, location not found
```

---

## 9. Preview System

### `PreviewWrapper.kt`

```kotlin
package com.coffeeshop.core.ui.preview

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.coffeeshop.core.ui.theme.CoffeeShopTheme

/**
 * Always wrap component @Preview functions with this.
 * Never call CoffeeShopTheme directly in individual previews.
 */
@Composable
fun PreviewWrapper(content: @Composable () -> Unit) {
    CoffeeShopTheme { content() }
}
```

### Preview annotation template — apply to EVERY component

```kotlin
// Add both annotations to every component file
@Preview(
    name = "Light",
    showBackground = true,
    backgroundColor = 0xFFFFF8F2
)
@Preview(
    name = "Dark",
    showBackground = true,
    backgroundColor = 0xFF120C05,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun ProductCardPreview() {
    PreviewWrapper {
        ProductCard(
            name              = "Cafe Mocha",
            description       = "Deep foam, rich espresso",
            price             = "₹220",
            imageUrl          = "",
            isFavourite       = false,
            onAddToCart       = {},
            onFavouriteToggle = {}
        )
    }
}
```

---

## 10. `MaterialTheme` Extension Properties

Add this file anywhere in `core-ui` — it makes token access clean across the project:

```kotlin
// core-ui/.../theme/ThemeExtensions.kt
package com.coffeeshop.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.coffeeshop.core.ui.tokens.Spacing

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current

// Usage in any composable anywhere in the project:
// val spacing = MaterialTheme.spacing
// Modifier.padding(horizontal = spacing.lg, vertical = spacing.md)
```

---

## 11. Compose Code Conventions

### Naming
- Screen composables: `XxxScreen` (e.g. `HomeScreen`, `ProductDetailScreen`)
- Reusable components: `CoffeeXxx` if generic brand component, just `XxxCard` / `XxxRow` if domain-specific
- Skeleton/shimmer counterparts: `XxxSkeleton` placed immediately below the real component in the same file
- Preview functions: `private fun XxxPreview()` — always `private`

### Parameter order (enforce strictly)
```kotlin
@Composable
fun MyComponent(
    // 1. Required data params
    title: String,
    subtitle: String,
    // 2. Optional data params with defaults
    isSelected: Boolean = false,
    // 3. Modifier — always second-to-last
    modifier: Modifier = Modifier,
    // 4. Trailing lambdas — always last
    onClick: () -> Unit
)
```

### State — what goes where
```kotlin
// ✅ Pure UI state — OK in composable with remember
var isExpanded by remember { mutableStateOf(false) }

// ❌ Business data — must be in ViewModel StateFlow
var products by remember { mutableStateOf(listOf<Product>()) }  // WRONG
```

### Strings
- All user-visible strings → `res/values/strings.xml`. No hardcoded strings in Compose.
- Access via `stringResource(R.string.xxx)`

### Accessibility
```kotlin
// Every clickable that isn't a Button/IconButton needs:
Modifier.semantics { contentDescription = "Add Cafe Mocha to cart" }

// Every Image:
AsyncImage(contentDescription = "Product image for Cafe Mocha")   // descriptive
AsyncImage(contentDescription = null)  // only for purely decorative images
```

### Never do these
```kotlin
// ❌ Hardcoded colour
Text(color = Color(0xFF4E2C0E))

// ❌ Hardcoded dp
Modifier.padding(16.dp)

// ❌ NavController in a composable parameter (outside AppNavGraph)
fun HomeScreen(navController: NavController)

// ❌ ViewModel inside core-ui component
fun ProductCard(viewModel: HomeViewModel = hiltViewModel())

// ❌ Feature import inside core-ui
import com.coffeeshop.feature.home.HomeViewModel
```

---

*End of DESIGN_SYSTEM.md reference file.*
*This file is loaded by the coffeeshop-design-system skill whenever UI code is generated.*
