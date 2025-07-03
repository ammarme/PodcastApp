package com.ammar.thmanyah.navigation

sealed class HomeRoutes(val route: String, val title: String) {
    data object Home : HomeRoutes("home", "Home")

    data object Search : HomeRoutes("search", "Search")
}