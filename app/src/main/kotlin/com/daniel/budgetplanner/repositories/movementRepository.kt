package com.daniel.budgetplanner.repositories

import com.daniel.budgetplanner.data.DashboardBalances
import com.daniel.budgetplanner.domain.entity.Movement
import kotlinx.coroutines.flow.Flow

typealias Movements = List<Movement>

interface MovementRepository {

    fun getMovements(startDate: String, endDate: String): Flow<Movements>

    fun getActualBalance(startDate: String, endDate: String, userName: String): Flow<DashboardBalances>

    fun getMovementsByName(startDate: String, endDate: String, user: String): Flow<Movements>

    suspend fun addMovementToDb(movement: Movement)

}