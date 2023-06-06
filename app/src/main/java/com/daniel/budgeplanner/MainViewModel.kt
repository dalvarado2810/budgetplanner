package com.daniel.budgeplanner

import com.daniel.budgeplanner.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor() :
    BaseViewModel<
            MainContract.Event,
            MainContract.State,
            MainContract.Effect
            >() {
    override fun createInitialState(): MainContract.State {
        return MainContract.State(
            MainContract.ScreenState.initial
        )
    }

    override fun handleEvent(event: MainContract.Event) {
        when(event) {
            is MainContract.Event.AddExpenses -> { setState { copy( screenState = MainContract.ScreenState.Success("Evento Gasto")) }}
            is MainContract.Event.AddIncomes -> {}
            is MainContract.Event.AddName -> {
                setState { copy( screenState = MainContract.ScreenState.Success("Evento Ingreso")) }
            }
            is MainContract.Event.ClearMonth -> {}
        }
    }
}