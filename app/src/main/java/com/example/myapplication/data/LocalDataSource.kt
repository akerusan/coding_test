package com.example.myapplication.data

import com.example.myapplication.data.database.dao.UserDao
import com.example.myapplication.data.database.entities.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val userDao: UserDao
){

    /** USERS LIST */

    suspend fun insertUsers(users: UserEntity) {
        userDao.insertUsers(users)
    }

    fun getUsers(): Flow<List<UserEntity>> {
        return userDao.getUsers()
    }
}