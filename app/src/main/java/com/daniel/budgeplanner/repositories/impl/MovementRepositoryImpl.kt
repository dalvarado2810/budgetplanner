package com.daniel.budgeplanner.repositories.impl

import com.daniel.budgeplanner.data.Category
import com.daniel.budgeplanner.data.room.MovementsDao
import com.daniel.budgeplanner.domain.entity.Movement
import com.daniel.budgeplanner.domain.entity.MovementType
import com.daniel.budgeplanner.repositories.MovementRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovementRepositoryImpl(
    private val movementsDao: MovementsDao
) : MovementRepository {

    override fun getMovements(startDate: String, endDate: String) = movementsDao
        .getAllMonthlyMovements(startDate, endDate)

    override fun getActualBalance(startDate: String, endDate: String): Flow<Int> = flow {
        val movements = movementsDao.getAllMonthlyMovements(startDate, endDate)

        movements.collect { list ->
            var balance = 0
            list.forEach { item ->
                when (item.movementType) {
                    MovementType.EXPENSE -> {
                        balance -= item.movementAmount
                    }
                    MovementType.INCOME -> {
                        balance += item.movementAmount
                    }
                }
            }
            emit(balance)
        }
    }

    override fun getAntExpenses(startDate: String, endDate: String): Flow<Int> = flow {
        val movements = movementsDao.getAllMonthlyMovements(startDate, endDate)

        movements.collect { list ->
            var balance = 0
            list.forEach { item ->
                when (item.movementCategory) {
                    Category.ANT_EXPENSES -> {
                        balance -= item.movementAmount
                    }
                    else -> { }
                }
            }
            emit(balance)
        }
    }

    override fun getFoodExpenses(startDate: String, endDate: String): Flow<Int> = flow {
        val movements = movementsDao.getAllMonthlyMovements(startDate, endDate)

        movements.collect { list ->
            var balance = 0
            list.forEach { item ->
                when (item.movementCategory) {
                    Category.FOOD_EXPENSES -> {
                        balance -= item.movementAmount
                    }
                    else -> { }
                }
            }
            emit(balance)
        }
    }

    override suspend fun addMovementToDb(movement: Movement) = movementsDao.addMovement(movement)
}