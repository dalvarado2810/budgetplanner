package com.daniel.budgetplanner.presentation.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.daniel.base.utils.extension.CollectEffectWithLifecycle
import com.daniel.base.utils.extension.viewModelFlow
import com.daniel.budgetplanner.presentation.mvi.App
import com.daniel.budgetplanner.presentation.viewmodel.AppViewModel
import com.daniel.budgetplanner.ui.screen.AppScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppRoute(
    viewModel: AppViewModel = koinViewModel()
) {
    val viewState by viewModel.state.collectAsStateWithLifecycle()
    val navcontroller = rememberNavController()

    LaunchedEffect(navcontroller)  {
        navcontroller
            .viewModelFlow()
            .flowOn(Dispatchers.IO)
            .collectLatest { currentViewModel ->
                val mainState = viewState

                if (mainState is App.State.Content) {
                    viewModel.updateStateAction(
                        mainState
                    )
                }
            }
    }

    CollectEffectWithLifecycle(flow = viewModel.effect) { effect ->
        when (effect) {
            else -> {}
        }
    }

    AppScreen(
        state = viewState,
        navController = navcontroller
    )
}