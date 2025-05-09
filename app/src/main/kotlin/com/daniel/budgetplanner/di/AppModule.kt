package com.daniel.budgetplanner.di

import com.daniel.budgetplanner.domain.usecase.StartUpUseCase
import com.daniel.budgetplanner.presentation.action.StartUpActionProcessor
import com.daniel.budgetplanner.presentation.action.UpdateStateActionProcessor
import com.daniel.budgetplanner.presentation.viewmodel.AppViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val appModule = module {
    /* ViewModel */
    viewModelOf(::AppViewModel)

    /* ActionProcessor */
    factoryOf(::StartUpActionProcessor)
    factoryOf(::UpdateStateActionProcessor)

    /* UseCase */
    factoryOf(::StartUpUseCase)
}