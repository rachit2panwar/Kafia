pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Kafia"
include(":app")

// Core modules
include(":core:core-navigation")
include(":core:core-ui")
include(":core:core-network")
include(":core:core-data")
include(":core:core-domain")
include(":core:core-location")

// Feature modules
include(":feature:feature-auth")
include(":feature:feature-home")
include(":feature:feature-detail")
include(":feature:feature-cart")
include(":feature:feature-orders")
include(":feature:feature-profile")
include(":feature:feature-favourites")
