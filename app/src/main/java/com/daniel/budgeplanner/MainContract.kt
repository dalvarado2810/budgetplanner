package com.daniel.budgeplanner

import com.daniel.budgeplanner.base.UiEffect
import com.daniel.budgeplanner.base.UiEvent
import com.daniel.budgeplanner.base.UiState

class MainContract {

    sealed class Event: UiEvent {
        data class AddName(val name: String): Event()
        object AddExpenses: Event()
        object AddIncomes: Event()
        object ObtainAllMovements: Event()
    }

    data class State(
        val screenState: ScreenState
    ): UiState

    sealed class ScreenState {
        object initial: ScreenState()
        object Loading: ScreenState()
        object error: ScreenState()
        data class Success(val data: String): ScreenState()
    }

    sealed class Effect: UiEffect {
        object ShowError: Effect()
        object ShowWarning: Effect()
    }
}