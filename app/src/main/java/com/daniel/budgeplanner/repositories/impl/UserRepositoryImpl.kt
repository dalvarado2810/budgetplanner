package com.daniel.budgeplanner.repositories.impl

import com.daniel.budgeplanner.data.room.UsersDao
import com.daniel.budgeplanner.domain.entity.User
import com.daniel.budgeplanner.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val usersDao: UsersDao
): UserRepository {

    override fun getUser(userName: String): Flow<User> {
        return flowOf(usersDao.findUserByName(userName = userName))
    }

    suspend fun addUser(user: User) = usersDao.addUser(user = user)
    suspend fun deleteUser(user: User) = usersDao.deleteUser(user = user)
    fun findUserByName(userName: String) = usersDao.findUserByName(userName = userName)


}