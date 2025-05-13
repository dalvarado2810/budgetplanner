package com.daniel.budgetplanner.domain.usecase

import com.daniel.base.domain.usecase.FlowUseCase
import com.daniel.budgetplanner.domain.usecase.model.StartUpResult
import com.daniel.budgetplanner.onboarding.navigation.OnboardingDestination
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class StartUpUseCase : FlowUseCase<Unit, StartUpResult, Nothing>() {
    override suspend fun executeOnBackground(params: Unit): Flow<StartUpResult> {
        return flowOf(
            StartUpResult (
                startDestination = OnboardingDestination.NavGraph
            )
        )
    }
}