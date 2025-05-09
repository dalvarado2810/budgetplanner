package com.daniel.budgetplanner.presentation.action

import com.daniel.base.domain.usecase.UseCaseState
import com.daniel.base.presentation.Mutation
import com.daniel.base.presentation.action.ActionProcessor
import com.daniel.budgetplanner.domain.usecase.StartUpUseCase
import com.daniel.budgetplanner.presentation.mvi.App
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StartUpActionProcessor(
    private val startUpUseCase: StartUpUseCase
) : ActionProcessor<App.State, App.Action.StartUp, App.Effect>() {
    override fun process(
        action: App.Action.StartUp,
        sideEffect: (App.Effect) -> Unit
    ): Flow<Mutation<App.State>> {
        return startUpUseCase.execute(Unit)
            .map { useCaseState ->
                when (useCaseState) {
                   is UseCaseState.Loading -> { _ ->
                       App.State.Starting
                   }

                    is UseCaseState.Data -> { _ ->
                        App.State.Content(
                            startDestination = useCaseState.value.startDestination
                        )
                    }

                    is UseCaseState.Error -> { _ ->
                        App.State.Failed
                    }
                }
            }
    }
}