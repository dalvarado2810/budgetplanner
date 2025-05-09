package com.daniel.budgetplanner.presentation.viewmodel

import com.daniel.base.presentation.Mutation
import com.daniel.base.presentation.viewmodel.BaseViewModel
import com.daniel.budgetplanner.presentation.action.StartUpActionProcessor
import com.daniel.budgetplanner.presentation.action.UpdateStateActionProcessor
import com.daniel.budgetplanner.presentation.mvi.App
import kotlinx.coroutines.flow.Flow

class AppViewModel(
    private val startUpActionProcessor: StartUpActionProcessor,
    private val updateStateActionProcessor: UpdateStateActionProcessor
) : BaseViewModel<App.State, App.Action, App.Effect>(
    initialState = App.State.Starting,
    initialAction = App.Action.StartUp
) {
    fun updateStateAction(state: App.State) =
        sendAction(App.Action.UpdateState(state))

    override fun processAction(
        action: App.Action,
        sideEffect: (App.Effect) -> Unit
    ): Flow<Mutation<App.State>> {
        return when (action) {
            is App.Action.StartUp ->
                startUpActionProcessor.process(action, sideEffect)
            is App.Action.UpdateState ->
                updateStateActionProcessor.process(action, sideEffect)
        }
    }

}