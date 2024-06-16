package com.example.myapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.data.database.dao.UserDao
import com.example.myapplication.data.database.entities.UserEntity


@TypeConverters(TypeConverter::class)
@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}