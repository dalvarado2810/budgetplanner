package com.daniel.budgetplanner.repositories

import com.daniel.budgetplanner.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(userName: String): Flow<User>
}