package com.daniel.budgeplanner.repositories

import com.daniel.budgeplanner.domain.entity.Movement
import kotlinx.coroutines.flow.Flow

typealias Movements = List<Movement>

interface MovementRepository {

    fun getMovements(startDate: String, endDate: String): Flow<Movements>

    fun getActualBalance(startDate: String, endDate: String): Flow<Int>

    fun getAntExpenses(startDate: String, endDate: String): Flow<Int>

    fun getFoodExpenses(startDate: String, endDate: String): Flow<Int>

    suspend fun addMovementToDb(movement: Movement)

}