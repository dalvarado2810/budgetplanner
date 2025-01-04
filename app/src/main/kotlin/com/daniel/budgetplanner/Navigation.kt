package com.daniel.budgetplanner

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.daniel.budgetplanner.ui.BudgetDashboard
import com.daniel.budgetplanner.ui.routers.GetStartedRouter
import com.daniel.budgetplanner.ui.routers.ShowUserNameRouter
import com.daniel.budgetplanner.ui.MonthlyPlanner
import com.daniel.budgetplanner.ui.Onboarding
import com.daniel.budgetplanner.utils.ScreensNavigation

@Composable
fun Navigation(viewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreensNavigation.GetStarted.routes
    ) {
        composable(route = ScreensNavigation.GetStarted.routes) {
            GetStartedRouter(viewModel = viewModel)
        }
        composable(route = ScreensNavigation.OnBoarding.routes) {
            Onboarding(navController = navController, viewModel = viewModel)
        }
        composable(route = ScreensNavigation.MonthlyPlanner.routes) {
            MonthlyPlanner(navController = navController, viewModel = viewModel)
        }
        composable(route = ScreensNavigation.BudgetDashboard.routes){
            BudgetDashboard(viewModel = viewModel)
        }
        composable(route = ScreensNavigation.ShowUserName.routes){
            ShowUserNameRouter(viewModel = viewModel, navController = navController)
        }
    }
}