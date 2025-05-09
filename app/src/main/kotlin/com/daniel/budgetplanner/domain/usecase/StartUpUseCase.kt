package com.daniel.budgetplanner.domain.usecase

import com.daniel.base.domain.usecase.FlowUseCase
import com.daniel.base.presentation.model.Destination
import com.daniel.budgetplanner.domain.usecase.model.StartUpResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.serialization.Serializable

class StartUpUseCase : FlowUseCase<Unit, StartUpResult, Nothing>() {
    override suspend fun executeOnBackground(params: Unit): Flow<StartUpResult> {
        return flowOf(
            StartUpResult (
                startDestination =   AppDestination.NavGraph
            )
        )
    }
}

@Serializable
sealed class AppDestination : Destination() {
    @Serializable
    data object NavGraph : AppDestination()
}