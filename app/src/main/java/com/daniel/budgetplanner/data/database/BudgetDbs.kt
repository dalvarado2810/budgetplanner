package com.daniel.budgetplanner.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.daniel.budgetplanner.data.Converters
import com.daniel.budgetplanner.data.room.MovementsDao
import com.daniel.budgetplanner.data.room.UsersDao
import com.daniel.budgetplanner.domain.entity.Movement
import com.daniel.budgetplanner.domain.entity.User

@Database(entities = [User::class], version = 1)
abstract class DbUsers: RoomDatabase() {
    abstract fun usersDao(): UsersDao
}

@Database(entities = [Movement::class], version = 2)
@TypeConverters(Converters::class)
abstract class DbMovements: RoomDatabase() {
    abstract val movementsDao: MovementsDao
}