package com.daniel.budgeplanner.data.room

import androidx.room.*
import com.daniel.budgeplanner.di.DB_MOVEMENTS
import com.daniel.budgeplanner.domain.entity.MovementType
import com.daniel.budgeplanner.domain.entity.Movement
import com.daniel.budgeplanner.domain.entity.User
import com.daniel.budgeplanner.repositories.Movements
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

    @Query("SELECT * FROM $DB_MOVEMENTS WHERE month = :month")
    fun getAllMonthlyMovements(month: Int): Flow<Movements>

    @Delete
    suspend fun deleteMovement(movement: Movement)
}