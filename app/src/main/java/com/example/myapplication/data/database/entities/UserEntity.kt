package com.example.myapplication.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.data.models.User
import com.example.myapplication.utils.Constants.USER_TABLE

@Entity(tableName = USER_TABLE)
data class UserEntity(val user: List<User>) {

    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}