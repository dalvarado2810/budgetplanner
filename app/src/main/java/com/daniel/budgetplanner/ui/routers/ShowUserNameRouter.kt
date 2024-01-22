package com.daniel.budgetplanner.ui.routers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.daniel.budgetplanner.MainContract
import com.daniel.budgetplanner.MainViewModel
import com.daniel.budgetplanner.ui.composables.ShowLoadingCircle
import com.daniel.budgetplanner.ui.composables.ShowNameInScreen
import com.daniel.budgetplanner.utils.ScreensNavigation
import kotlinx.coroutines.delay

private const val MILLIS = 2000L

@Composable
fun ShowUserNameRouter(
    navController: NavController,
    viewModel: MainViewModel) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val nameState by produceState<MainContract.ScreenState>(
        initialValue = MainContract.ScreenState.Loading,
        key1 = lifecycle,
        key2 = viewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            viewModel.setAction(MainContract.Action.ObtainUserName)
            delay(MILLIS)
            viewModel.uiState.collect { value = it.screenState }
        }
    }

    when (nameState) {
        is MainContract.ScreenState.Loading, MainContract.ScreenState.Initial -> ShowLoadingCircle()
        is MainContract.ScreenState.Success -> ShowNameInScreen(
            name = (nameState as MainContract.ScreenState.Success).data,
            event = viewModel.event,
            onClick = { viewModel.setAction(MainContract.Action.ChangeName("")) },
            goNextScreen = {
                viewModel.setAction(MainContract.Action.SetSuccessState)
                navController.navigate(route = ScreensNavigation.MonthlyPlanner.routes)
            }
        )
        is MainContract.ScreenState.Error -> {}
    }
}