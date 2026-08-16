# Kafia — Coffee Shop Ordering App

Kafia is a modern, high-performance coffee shop ordering application built for Android. It provides a seamless experience for users to browse, customize, and order their favorite coffee products, featuring a design inspired by premium coffee aesthetics.

## 🚀 Key Features

- **Personalized Onboarding**: Secure login and authentication flow.
- **Location-Aware**: Integrated location services to verify delivery zones and provide localized content.
- **Rich Product Catalog**: Browse a wide variety of coffee products with real-time filtering and sorting.
- **Detailed Customization**: Select sizes and view deep product descriptions before adding to the cart.
- **Smart Cart Management**: Easily manage your bag and view a detailed payment breakdown.
- **Favorites & Order History**: Save your preferred drinks and track your previous orders.
- **Edge-to-Edge UI**: A premium, immersive experience with transparent system bars.

## 🛠 Tech Stack

- **UI**: [Jetpack Compose](https://developer.android.com/jetpack/compose) for a modern, declarative UI.
- **Language**: 100% [Kotlin](https://kotlinlang.org/).
- **Architecture**: MVVM + Clean Architecture with a multi-module setup.
- **Dependency Injection**: [Hilt](https://dagger.dev/hilt/) for robust and scalable DI.
- **Navigation**: [Jetpack Navigation Compose](https://developer.android.com/guide/navigation/navigation-getting-started) (Navigation 3).
- **Networking**: [Retrofit](https://square.github.io/retrofit/) + [OkHttp](https://square.github.io/okhttp/) + [Gson](https://github.com/google/gson).
- **Database**: [Room](https://developer.android.com/training/data-storage/room) for local persistence.
- **Image Loading**: [Coil](https://coil-kt.github.io/coil/) for asynchronous image loading.
- **Local Storage**: [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) for user preferences and tokens.

## 🏛 Architecture

The project follows a **Modularized Clean Architecture** approach to ensure scalability and maintainability:

- **`:app`**: The main entry point, housing the NavHost and high-level DI.
- **`:feature:*`**: Feature-specific modules (Home, Detail, Cart, Auth, etc.) that encapsulate their own UI, domain, and data layers.
- **`:core:core-ui`**: Shared design system, theme, and reusable UI components.
- **`:core:core-domain`**: Pure Kotlin domain models and shared business logic.
- **`:core:core-data`**: Shared repositories and database implementations.
- **`:core:core-network`**: Networking infrastructure and base API clients.

## 🎨 Design Reference

The application UI is based on the premium **Coffee Shop Mobile App Design** community file.
- **Figma Design**: [View on Figma](https://www.figma.com/design/M4VO9FuT9mV1sWUgRXlaaT/Coffee-Shop-Mobile-App-Design--Community-?node-id=2-2)

## 🛠 Setup & Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/Kafia.git
   ```
2. Open the project in **Android Studio Ladybug (or newer)**.
3. Sync the project with Gradle files.
4. Run the app on an emulator or a physical device (Min SDK 26).

## 📄 License

This project is for demonstration purposes. Refer to the project's root for license details.
