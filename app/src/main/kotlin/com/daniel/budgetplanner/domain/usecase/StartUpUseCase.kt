package com.daniel.budgetplanner.domain.usecase

import com.daniel.base.domain.repository.StorageRepository
import com.daniel.base.domain.usecase.FlowUseCase
import com.daniel.budgetplanner.dashboard.navigation.DashboardDestination
import com.daniel.budgetplanner.domain.usecase.model.StartUpResult
import com.daniel.budgetplanner.onboarding.navigation.OnboardingDestination
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class StartUpUseCase(
    val storageRepository: StorageRepository
) : FlowUseCase<Unit, StartUpResult, Nothing>() {
    override suspend fun executeOnBackground(params: Unit): Flow<StartUpResult> {
        val destination = if (storageRepository.getUser().isNullOrEmpty()) OnboardingDestination.NavGraph
        else DashboardDestination.NavGraph
        return flowOf(
            StartUpResult (
                startDestination = destination
            )
        )
    }
}