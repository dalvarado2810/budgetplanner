package com.daniel.budgeplanner.ui.routers

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.daniel.budgeplanner.MainActivity
import com.daniel.budgeplanner.MainContract
import com.daniel.budgeplanner.MainViewModel
import com.daniel.budgeplanner.domain.entity.Movement
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
    val context = LocalContext.current
    val itemList by viewModel.monthlyMovements.collectAsState(initial = emptyList())
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
        is MainContract.ScreenState.Success -> GetStarted (
            onClickContinue = {
                navController.navigate(
                    navigateRoute(
                        (nameState as MainContract.ScreenState.Success).data,
                        itemList
                    )
                )
            },
            dateSelectionAction = { start, end ->
                viewModel.setAction(MainContract.Action.SetRangeDate(
                    startDate = start,
                    endDate = end)
                )
            }
        )
        is MainContract.ScreenState.error -> {
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(context, intent, null)
        }
    }
}

private fun navigateRoute(name: String, list: List<Movement>): String {
    return when {
        (name.isNotEmpty() && list.isEmpty()) -> ScreensNavigation.MonthlyPlanner.routes
        (name.isNotEmpty() && list.isNotEmpty()) -> ScreensNavigation.BudgetDashboard.routes
        else -> ScreensNavigation.OnBoarding.routes
    }
}

