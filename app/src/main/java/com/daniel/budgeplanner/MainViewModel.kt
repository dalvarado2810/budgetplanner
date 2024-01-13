package com.daniel.budgeplanner

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.daniel.budgeplanner.base.BaseViewModel
import com.daniel.budgeplanner.data.Steps
import com.daniel.budgeplanner.data.sharedpreferences.AppPreference
import com.daniel.budgeplanner.domain.entity.Movement
import com.daniel.budgeplanner.repositories.MovementRepository
import com.daniel.budgeplanner.utils.EMPTY_STRING
import com.daniel.budgeplanner.utils.END_DATE
import com.daniel.budgeplanner.utils.START_DATE
import com.daniel.budgeplanner.utils.USER_NAME
import com.daniel.budgeplanner.utils.getDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
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
        >(
) {
    val monthlyMovements = repository.getMovementsByName(getRangeOfDates().first,getRangeOfDates().second, getUserName())
    val actualBalance = repository.getActualBalance(getRangeOfDates().first,getRangeOfDates().second,getUserName())

    override fun createInitialState(): MainContract.State {
        return MainContract.State(
            MainContract.ScreenState.Initial,
            MainContract.OnboardingState.Loading,
            MainContract.DashboardState.Loading
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
                    copy( onboardingState =
                    MainContract.OnboardingState.Loading)
                }
            }
            is MainContract.Action.ChangeName -> {
                setEvent { MainContract.Event.SetNewName(getUserName()) }
            }
            is MainContract.Action.SetRangeDate -> {
                setPeriodRangeDates(
                    startDate = action.startDate,
                    endDate = action.endDate)
            }
            is MainContract.Action.NextStep -> {
                when (action.step){
                    Steps.STEP_ONE -> {
                        setState {
                            copy( onboardingState =
                            MainContract.OnboardingState.StepTwo)
                        }
                    }
                    Steps.STEP_TWO -> {
                        setState {
                            copy( onboardingState =
                            MainContract.OnboardingState.StepThree)
                        }
                    }
                    Steps.STEP_THREE -> {
                        setState {
                            copy( onboardingState =
                            MainContract.OnboardingState.Finish)
                        }
                    }

                }
                setState {
                    copy( screenState =
                    MainContract.ScreenState.Initial)
                }
            }
            is MainContract.Action.ObtainMovements -> {
                setState {
                    copy(
                        dashboardState =
                        MainContract.DashboardState.Success
                    )
                }
            }
        }
    }

    fun getUserName(): String = sharedPreferences.getString(USER_NAME) ?: EMPTY_STRING

    private fun setUserNameState() {
        if(getUserName().isNotEmpty()) {
            setState {
                copy(
                    onboardingState =
                    MainContract.OnboardingState.NoShowOnboarding
                )
            }
        } else {
            setState {
                copy(
                    onboardingState =
                    MainContract.OnboardingState.StepOne
                )
            }
        }
    }

    private fun setPeriodRangeDates(
        startDate: LocalDate?,
        endDate: LocalDate?
    ){
        sharedPreferences.setString(START_DATE, startDate.toString())
        sharedPreferences.setString(END_DATE, endDate.toString())
        setState {
            copy( screenState =
            MainContract.ScreenState.Error)
        }
    }

    fun getRangeOfDates(): Pair<String, String> {
        val isMonthlyPeriodSet = sharedPreferences.getString(START_DATE) != null &&
                sharedPreferences.getString(END_DATE) != null
        return if (isMonthlyPeriodSet) {
            Pair(sharedPreferences.getString(START_DATE) ?: "",
                sharedPreferences.getString(END_DATE) ?: "")
        } else {
            Pair(getDate().first,
                getDate().second)
        }
    }
}