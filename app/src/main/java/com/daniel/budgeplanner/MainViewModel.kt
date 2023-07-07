package com.daniel.budgeplanner

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.daniel.budgeplanner.base.BaseViewModel
import com.daniel.budgeplanner.data.sharedpreferences.AppPreference
import com.daniel.budgeplanner.domain.entity.Movement
import com.daniel.budgeplanner.repositories.MovementRepository
import com.daniel.budgeplanner.utils.EMPTY_STRING
import com.daniel.budgeplanner.utils.USER_NAME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val context: Application,
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

    private fun addMovement(movement: Movement) {
        viewModelScope.launch {
            repository.addMovementToDb(movement)
        }
    }

    override fun handleEvent(event: MainContract.Event) {
        when(event) {
            is MainContract.Event.AddMovements -> {
                addMovement(event.movement)
                setState {
                    copy( screenState =
                    MainContract.ScreenState.Success(
                        context.getString(R.string.movement_saved)))
                }
            }
            is MainContract.Event.AddName -> {
                sharedPreferences.setString(USER_NAME, event.name)
            }
            is MainContract.Event.ObtainAllMovements -> { }
            is MainContract.Event.setSuccessState -> {
                setState {
                    copy( screenState =
                    MainContract.ScreenState.initial)
                }
            }
        }
    }

    private fun getMonth(): Int {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.MONTH) + 1
    }

    fun isUserCreated(): Boolean = sharedPreferences.getString(USER_NAME) != EMPTY_STRING

    fun getUserName(): String = sharedPreferences.getString(USER_NAME) ?: EMPTY_STRING
}