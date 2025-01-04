package com.daniel.budgetplanner.data.room

import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy
import androidx.room.Delete
import androidx.room.Dao
import com.daniel.budgetplanner.di.DB_MOVEMENTS
import com.daniel.budgetplanner.domain.entity.Movement
import com.daniel.budgetplanner.domain.entity.User
import com.daniel.budgetplanner.repositories.Movements
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM DB_USERS WHERE userName = :userName")
    fun findUserByName(userName: String): User

    @Delete
    suspend fun deleteUser(user: User)
}

@Dao
interface MovementsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovement(movement: Movement)

    @Query("SELECT * FROM $DB_MOVEMENTS WHERE date BETWEEN :startDate AND :endDate" )
    fun getAllMonthlyMovements(startDate: String, endDate: String): Flow<Movements>

    @Delete
    suspend fun deleteMovement(movement: Movement)

    @Query("SELECT * FROM $DB_MOVEMENTS WHERE date BETWEEN :startDate AND :endDate AND movementUser = :user" )
    fun getAllMonthlyMovementsByUser(startDate: String, endDate: String, user: String): Flow<Movements>

}