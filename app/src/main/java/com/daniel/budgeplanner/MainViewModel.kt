package com.daniel.budgeplanner

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.daniel.budgeplanner.base.BaseViewModel
import com.daniel.budgeplanner.data.sharedpreferences.AppPreference
import com.daniel.budgeplanner.domain.entity.Movement
import com.daniel.budgeplanner.repositories.MovementRepository
import com.daniel.budgeplanner.utils.USER_NAME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MovementRepository,
    private val sharedPreferences: AppPreference
) : BaseViewModel<
            MainContract.Event,
            MainContract.State,
            MainContract.Effect
            >() {

    val monthlyMovements = repository.getMovements(getMonth())
    val actualBalance = repository.getActualBalance(getMonth())

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
                sharedPreferences.setString(USER_NAME, event.name)
            }
            is MainContract.Event.ObtainAllMovements -> {}
        }
    }

    private fun getMonth(): Int {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.MONTH) + 1
    }

    fun isUserCreated(): Boolean = sharedPreferences.getString(USER_NAME) != ""

    fun getUserName(): String = sharedPreferences.getString(USER_NAME) ?: ""
}