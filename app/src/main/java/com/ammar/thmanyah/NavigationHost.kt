package com.ammar.thmanyah

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ammar.thmanyah.feature.home.ui.HomeSectionScreen
import com.ammar.thmanyah.feature.search.presentation.ui.SearchScreen
import com.ammar.thmanyah.navigation.HomeRoutes

@Composable
fun NavigationHost(
    navController: NavHostController,
    innerPadding: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoutes.Home.route,
        modifier = Modifier.padding(innerPadding),
    ) {
        composable(HomeRoutes.Home.route) { HomeSectionScreen(Modifier) }
        composable(HomeRoutes.Search.route) { SearchScreen(Modifier) }
    }
}
