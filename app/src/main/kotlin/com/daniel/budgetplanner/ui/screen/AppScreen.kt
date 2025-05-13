package com.daniel.budgetplanner.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
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

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    Box {
        Scaffold(
            modifier = Modifier,
        ) { _ ->
            ProductsNavHost(
                modifier = Modifier,
                startDestination = state.startDestination,
                navController = navController
            )
        }
    }
}