package com.daniel.budgeplanner

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.daniel.budgeplanner.base.BaseViewModel
import com.daniel.budgeplanner.data.sharedpreferences.AppPreference
import com.daniel.budgeplanner.domain.entity.Movement
import com.daniel.budgeplanner.repositories.MovementRepository
import com.daniel.budgeplanner.utils.EMPTY_STRING
import com.daniel.budgeplanner.utils.USER_NAME
import com.daniel.budgeplanner.utils.getDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val context: Application,
    private val repository: MovementRepository,
    private val sharedPreferences: AppPreference
) : BaseViewModel<
            MainContract.Action,
            MainContract.State,
            MainContract.Event
            >() {

    val monthlyMovements = repository.getMovements(getDate().first, getDate().second)
    val actualBalance = repository.getActualBalance(getDate().first, getDate().second)
    val antExpensesBalance = repository.getAntExpenses(getDate().first, getDate().second)
    val foodExpensesBalance = repository.getFoodExpenses(getDate().first, getDate().second)

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

    override fun handleAction(action: MainContract.Action) {
        when(action) {
            is MainContract.Action.AddMovements -> {
                addMovement(action.movement)
                setState {
                    copy( screenState =
                    MainContract.ScreenState.Success(
                        context.getString(R.string.movement_saved)))
                }
            }
            is MainContract.Action.AddName -> {
                sharedPreferences.setString(USER_NAME, action.name)
            }
            is MainContract.Action.ObtainUserName -> {
                setUserNameState()
            }
            is MainContract.Action.SetSuccessState -> {
                setState {
                    copy( screenState =
                    MainContract.ScreenState.initial)
                }
            }
            is MainContract.Action.ChangeName -> {
                setEvent { MainContract.Event.SetNewName(getUserName()) }
            }
        }
    }

    fun getUserName(): String = sharedPreferences.getString(USER_NAME) ?: EMPTY_STRING

    private fun setUserNameState() {
        setState {
            copy(
                screenState =
                MainContract.ScreenState.Success(
                    getUserName()
                )
            )
        }
    }
}