package com.daniel.budgeplanner.ui.routers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.daniel.budgeplanner.MainContract
import com.daniel.budgeplanner.MainViewModel
import com.daniel.budgeplanner.ui.GetStarted
import com.daniel.budgeplanner.ui.composables.LoadingAnimation
import com.daniel.budgeplanner.utils.ScreensNavigation
import kotlinx.coroutines.delay

@Composable
fun GetStartedRouter(
    navController: NavController,
    viewModel: MainViewModel
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val nameState by produceState<MainContract.ScreenState>(
        initialValue = MainContract.ScreenState.Loading,
        key1 = lifecycle,
        key2 = viewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            viewModel.setAction(MainContract.Action.ObtainUserName)
            delay(2000)
            viewModel.uiState.collect { value = it.screenState }
        }
    }

    when (nameState) {
        is MainContract.ScreenState.Loading,
            MainContract.ScreenState.initial -> LoadingAnimation()
        is MainContract.ScreenState.Success -> GetStarted {
            navController.navigate(
                navigateRoute((nameState as MainContract.ScreenState.Success).data))
        }
        is MainContract.ScreenState.error -> {}
    }
}

private fun navigateRoute(name: String): String {
    return if (name.isNotEmpty()) {
        ScreensNavigation.ShowUserName.routes
    } else ScreensNavigation.OnBoarding.routes
}