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
