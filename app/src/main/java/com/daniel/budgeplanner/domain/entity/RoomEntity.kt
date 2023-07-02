package com.daniel.budgeplanner.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.daniel.budgeplanner.data.Category
import com.daniel.budgeplanner.di.DB_MOVEMENTS

@Entity(tableName = "DB_USERS")
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Int,
    val userName: String
)

@Entity(tableName = DB_MOVEMENTS)
data class Movement(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "movementID")
    val id: Int,
    val movementDescription: String,
    val movementAmount: String,
    val movementType: MovementType,
    val movementUser: String,
    val movementCategory: Category,
    val date: String,
    val month: Int,
    val year: Int
)

enum class MovementType {
    INCOME,
    EXPENSE
}
