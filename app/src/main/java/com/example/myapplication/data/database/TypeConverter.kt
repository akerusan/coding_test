package com.example.myapplication.data.database

import androidx.room.TypeConverter
import com.example.myapplication.data.models.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverter {

    private var gson = Gson()

    /** User <-> String */
    @TypeConverter
    fun userListToString(list: List<User>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun stringToUserList(data: String): List<User> {
        val listType = object : TypeToken<List<User>>(){}.type
        return gson.fromJson(data, listType)
    }
}