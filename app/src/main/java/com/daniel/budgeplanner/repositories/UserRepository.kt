package com.daniel.budgeplanner.repositories

import com.daniel.budgeplanner.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(userName: String): Flow<User>
}