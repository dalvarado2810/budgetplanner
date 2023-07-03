package com.daniel.budgeplanner.repositories.impl

import com.daniel.budgeplanner.data.room.MovementsDao
import com.daniel.budgeplanner.domain.entity.Movement
import com.daniel.budgeplanner.domain.entity.MovementType
import com.daniel.budgeplanner.repositories.MovementRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.forEach

class MovementRepositoryImpl(
    private val movementsDao: MovementsDao
) : MovementRepository {

    override fun getMovements(month: Int) = movementsDao
        .getAllMonthlyMovements(month)

    override fun getActualBalance(month: Int): Flow<Int> = flow {
        val movements = movementsDao.getAllMonthlyMovements(month)

        movements.collect { list ->
            var balance = 0
            list.forEach { item ->
                when(item.movementType) {
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

    override suspend fun addMovementToDb(movement: Movement) = movementsDao
        .addMovement(movement)

}