package com.daniel.budgeplanner.repositories

import com.daniel.budgeplanner.domain.entity.Movement
import kotlinx.coroutines.flow.Flow

typealias Movements = List<Movement>

interface MovementRepository {

    fun getMovements(month: Int): Flow<Movements>

    suspend fun addMovementToDb(movement: Movement)

}