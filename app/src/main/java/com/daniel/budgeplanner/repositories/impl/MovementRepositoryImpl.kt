package com.daniel.budgeplanner.repositories.impl

import com.daniel.budgeplanner.data.room.MovementsDao
import com.daniel.budgeplanner.domain.entity.Movement
import com.daniel.budgeplanner.repositories.MovementRepository

class MovementRepositoryImpl(
    private val movementsDao: MovementsDao
) : MovementRepository {

    override fun getMovements(month: Int) = movementsDao
        .getAllMonthlyMovements(month)

    override suspend fun addMovementToDb(movement: Movement) = movementsDao
        .addMovement(movement)

}