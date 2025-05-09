package com.daniel.budgetplanner.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.daniel.budgetplanner.ProductsNavHost
import com.daniel.budgetplanner.presentation.mvi.App

@Composable
fun AppScreen(
    state: App.State,
    navController: NavHostController
) {
    if (state !is App.State.Content) return

    Box() {
        Scaffold(
            modifier = Modifier,
        ) { innerPadding ->
            ProductsNavHost(
                modifier = Modifier.padding(innerPadding),
                startDestination = state.startDestination,
                navController = navController
            )
        }
    }
}