package com.daniel.budgeplanner

import com.daniel.budgeplanner.base.UiEvent
import com.daniel.budgeplanner.base.UiAction
import com.daniel.budgeplanner.base.UiState
import com.daniel.budgeplanner.domain.entity.Movement

class MainContract {

    sealed class Action: UiAction {
        data class AddName(val name: String): Action()
        data class AddMovements(val movement: Movement): Action()
        object SetSuccessState: Action()
        object ObtainUserName: Action()

        data class ChangeName(val name: String): Action()
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

    sealed class Event: UiEvent {
        object ShowError: Event()
        object ShowWarning: Event()

        data class SetNewName(val name: String): Event()
    }
}