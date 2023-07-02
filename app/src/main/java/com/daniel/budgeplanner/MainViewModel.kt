package com.daniel.budgeplanner

import androidx.lifecycle.viewModelScope
import com.daniel.budgeplanner.base.BaseViewModel
import com.daniel.budgeplanner.domain.entity.Movement
import com.daniel.budgeplanner.repositories.MovementRepository
import com.daniel.budgeplanner.repositories.impl.MovementRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MovementRepository
) : BaseViewModel<
            MainContract.Event,
            MainContract.State,
            MainContract.Effect
            >() {

    val monthlyMovements = repository.getMovements(getMonth())
    override fun createInitialState(): MainContract.State {
        return MainContract.State(
            MainContract.ScreenState.initial
        )
    }

    fun addMovement(movement: Movement) {
        viewModelScope.launch {
            repository.addMovementToDb(movement)
        }
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

    private fun getMonth(): Int {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.MONTH) + 1
    }
}