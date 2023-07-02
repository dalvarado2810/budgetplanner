package com.daniel.budgeplanner.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.daniel.budgeplanner.data.room.MovementsDao
import com.daniel.budgeplanner.data.room.UsersDao
import com.daniel.budgeplanner.domain.entity.Movement
import com.daniel.budgeplanner.domain.entity.User


@Database(
    entities = [User::class],
    version = 1
)
abstract class DbUsers: RoomDatabase() {
    abstract fun usersDao(): UsersDao
}

@Database(
    entities = [Movement::class],
    version = 2
)
abstract class DbMovements: RoomDatabase() {
    abstract val movementsDao: MovementsDao
}