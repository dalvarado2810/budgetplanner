package com.daniel.budgetplanner.presentation.action

import com.daniel.base.presentation.Mutation
import com.daniel.base.presentation.action.ActionProcessor
import com.daniel.budgetplanner.presentation.mvi.App
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class UpdateStateActionProcessor
    : ActionProcessor<App.State, App.Action.UpdateState, App.Effect>() {
    override fun process(
        action: App.Action.UpdateState,
        sideEffect: (App.Effect) -> Unit
    ): Flow<Mutation<App.State>> {
        return flowOf { _ ->
            action.state
        }
    }
}