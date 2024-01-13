package com.daniel.budgeplanner

import com.daniel.budgeplanner.base.UiEvent
import com.daniel.budgeplanner.base.UiAction
import com.daniel.budgeplanner.base.UiState
import com.daniel.budgeplanner.data.Steps
import com.daniel.budgeplanner.domain.entity.Movement
import java.time.LocalDate

class MainContract {

    sealed class Action: UiAction {
        data class AddName(val name: String): Action()
        data class AddMovements(val movement: Movement): Action()
        object SetSuccessState: Action()
        object ObtainUserName: Action()
        data class NextStep(val step: Steps): Action()
        data class ChangeName(val name: String): Action()
        object ObtainMovements: Action()

        data class SetRangeDate(
            val startDate: LocalDate?,
            val endDate: LocalDate?
        ): Action()
    }

    data class State(
        val screenState: ScreenState,
        val onboardingState: OnboardingState,
        val dashboardState: DashboardState
    ): UiState

    sealed class ScreenState {
        object Initial: ScreenState()
        object Loading: ScreenState()
        object Error: ScreenState()
        data class Success(val data: String): ScreenState()
    }

    sealed class OnboardingState {

        object Loading: OnboardingState()
        object NoShowOnboarding: OnboardingState()
        object StepOne: OnboardingState()
        object StepTwo: OnboardingState()
        object StepThree: OnboardingState()
        object Finish: OnboardingState()
    }

    sealed class DashboardState {

        object Loading: DashboardState()
        object Success: DashboardState()

    }

    sealed class Event: UiEvent {
        object ShowError: Event()
        object ShowWarning: Event()
        data class SetNewName(val name: String): Event()
    }
}