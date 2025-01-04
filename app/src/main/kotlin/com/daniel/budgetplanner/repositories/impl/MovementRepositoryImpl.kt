package com.daniel.budgetplanner.repositories.impl

import com.daniel.budgetplanner.data.Category
import com.daniel.budgetplanner.data.DashboardBalances
import com.daniel.budgetplanner.data.room.MovementsDao
import com.daniel.budgetplanner.domain.entity.Movement
import com.daniel.budgetplanner.domain.entity.MovementType
import com.daniel.budgetplanner.repositories.MovementRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovementRepositoryImpl(
    private val movementsDao: MovementsDao
) : MovementRepository {

    override fun getMovements(startDate: String, endDate: String) = movementsDao
        .getAllMonthlyMovements(startDate, endDate)

    override fun getActualBalance(
        startDate: String,
        endDate: String,
        userName: String
    ): Flow<DashboardBalances> = flow {
        val movements = movementsDao.getAllMonthlyMovementsByUser(startDate, endDate, userName)

        movements.collect { list ->
            var actualBalance = 0
            var monthlyBalance = 0
            var otherBalance = 0
            var foodBalance = 0
            var healthBalance = 0
            var servicesBalance = 0
            var transportBalance = 0
            var outfitBalance = 0
            var antBalance = 0
            list.forEach { item ->
                when (item.movementType) {
                    MovementType.EXPENSE -> {
                        actualBalance -= item.movementAmount
                    }

                    MovementType.INCOME -> {
                        actualBalance += item.movementAmount
                    }
                }
                when (item.movementCategory) {
                    Category.MONTHLY_INCOMES -> monthlyBalance += item.movementAmount
                    Category.OTHER_INCOMES -> otherBalance += item.movementAmount
                    Category.FOOD_EXPENSES -> foodBalance -= item.movementAmount
                    Category.HEALTH_EXPENSES -> healthBalance -= item.movementAmount
                    Category.SERVICES_EXPENSES -> servicesBalance -= item.movementAmount
                    Category.TRANSPORTATION_EXPENSES -> transportBalance -= item.movementAmount
                    Category.OUTFIT_EXPENSES -> outfitBalance -= item.movementAmount
                    Category.ANT_EXPENSES -> antBalance -= item.movementAmount
                }

                val data = DashboardBalances(
                    actualBalance = actualBalance,
                    monthlyIncomeBalance = monthlyBalance,
                    otherIncomesBalance = otherBalance,
                    foodExpensesBalance = foodBalance,
                    healthExpensesBalance = healthBalance,
                    servicesExpensesBalance = servicesBalance,
                    transportExpensesBalance = transportBalance,
                    outfitExpensesBalance = outfitBalance,
                    antExpensesBalance = antBalance
                )
                emit(data)
            }
        }
    }

    override fun getMovementsByName(
        startDate: String,
        endDate: String,
        user: String
    ) = movementsDao.getAllMonthlyMovementsByUser(startDate,endDate,user)

    override suspend fun addMovementToDb(movement: Movement) = movementsDao.addMovement(movement)
}