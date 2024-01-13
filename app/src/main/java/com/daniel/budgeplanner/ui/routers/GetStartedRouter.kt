package com.daniel.budgeplanner.ui.routers

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.daniel.budgeplanner.MainActivity
import com.daniel.budgeplanner.MainContract
import com.daniel.budgeplanner.MainViewModel
import com.daniel.budgeplanner.data.Steps
import com.daniel.budgeplanner.domain.entity.Movement
import com.daniel.budgeplanner.ui.BudgetDashboard
import com.daniel.budgeplanner.ui.GetStarted
import com.daniel.budgeplanner.ui.composables.LoadingAnimation
import com.daniel.budgeplanner.ui.composables.OnboardingSteps
import com.daniel.budgeplanner.utils.ScreensNavigation
import kotlinx.coroutines.delay

@Composable
fun GetStartedRouter(
    viewModel: MainViewModel
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val context = LocalContext.current

    val onboardingState by produceState<MainContract.OnboardingState>(
        initialValue = MainContract.OnboardingState.Loading
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            viewModel.setAction(MainContract.Action.ObtainUserName)
            delay(2000)
            viewModel.uiState.collect { value = it.onboardingState}
        }
    }

    when (onboardingState) {
        is MainContract.OnboardingState.Loading -> LoadingAnimation()
        is MainContract.OnboardingState.NoShowOnboarding -> {
            BudgetDashboard(viewModel = viewModel)
        }
        is MainContract.OnboardingState.StepOne -> {
            OnboardingSteps(step = Steps.STEP_ONE) {
                    viewModel.setAction(MainContract.Action.NextStep(Steps.STEP_ONE))
                }
        }
        is MainContract.OnboardingState.StepTwo -> {
            OnboardingSteps(step = Steps.STEP_TWO) {
                viewModel.setAction(MainContract.Action.NextStep(Steps.STEP_TWO))
            }
        }
        is MainContract.OnboardingState.StepThree -> {
            OnboardingSteps(step = Steps.STEP_THREE) {
                viewModel.setAction(MainContract.Action.NextStep(Steps.STEP_THREE))
            }
        }
        is MainContract.OnboardingState.Finish -> {
            GetStarted (
                dateSelectionAction = { start, end , name->
                    viewModel.setAction(MainContract.Action.SetRangeDate(
                        startDate = start,
                        endDate = end)
                    )
                    viewModel.setAction(MainContract.Action.AddName(name))
                    val intent = Intent(context, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(context, intent, null)
                }
            )
        }
    }
}