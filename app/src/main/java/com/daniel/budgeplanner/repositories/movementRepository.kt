package com.daniel.budgeplanner.repositories

import com.daniel.budgeplanner.domain.entity.Movement
import kotlinx.coroutines.flow.Flow

typealias Movements = List<Movement>

interface MovementRepository {

    fun getMovements(month: Int): Flow<Movements>

    fun getActualBalance(month: Int): Flow<Int>

    suspend fun addMovementToDb(movement: Movement)

}