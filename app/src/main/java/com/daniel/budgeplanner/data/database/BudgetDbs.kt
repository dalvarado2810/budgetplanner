package com.daniel.budgeplanner.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.daniel.budgeplanner.data.Converters
import com.daniel.budgeplanner.data.room.MovementsDao
import com.daniel.budgeplanner.data.room.UsersDao
import com.daniel.budgeplanner.domain.entity.Movement
import com.daniel.budgeplanner.domain.entity.User

@Database(entities = [User::class], version = 1)
abstract class DbUsers: RoomDatabase() {
    abstract fun usersDao(): UsersDao
}

@Database(entities = [Movement::class], version = 2)
@TypeConverters(Converters::class)
abstract class DbMovements: RoomDatabase() {
    abstract val movementsDao: MovementsDao
}