package com.daniel.budgeplanner

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.daniel.budgeplanner.ui.GetStarted
import com.daniel.budgeplanner.ui.MonthlyPlanner
import com.daniel.budgeplanner.ui.Onboarding
import com.daniel.budgeplanner.utils.ScreensNavigation

@Composable
fun Navigation (viewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreensNavigation.MonthlyPlanner.routes
    ) {
        composable(route = ScreensNavigation.GetStarted.routes) {
            GetStarted(navController = navController)
        }
        composable(route = ScreensNavigation.OnBoarding.routes) {
            Onboarding(navController = navController)
        }
        composable(route = ScreensNavigation.MonthlyPlanner.routes) {
            MonthlyPlanner(navController = navController, viewModel = viewModel)
        }
    }

}