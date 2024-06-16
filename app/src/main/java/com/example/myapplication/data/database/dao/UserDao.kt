package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.database.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    /** USERS LIST */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: UserEntity)

    @Query("SELECT * FROM user_table")
    fun getUsers(): Flow<List<UserEntity>>
}