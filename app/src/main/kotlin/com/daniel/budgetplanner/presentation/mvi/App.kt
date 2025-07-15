package com.daniel.budgetplanner.presentation.mvi

import com.daniel.base.presentation.ViewAction
import com.daniel.base.presentation.ViewEffect
import com.daniel.base.presentation.ViewState
import com.daniel.base.presentation.model.Destination
import com.daniel.base.presentation.model.DoNotThrottle

object App {
    sealed class State: ViewState() {
        data object Starting : State()

        data class Content(
            val startDestination: Destination
        ) : State()

        data object Failed : State()
    }

    sealed class Action : ViewAction() {
        data object StartUp : Action()

        @DoNotThrottle
        data class UpdateState(
            val state: State
        ) : Action()
    }

    sealed class Effect : ViewEffect()
}