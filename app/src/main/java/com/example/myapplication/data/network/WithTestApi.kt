package com.example.myapplication.data.network

import com.example.myapplication.data.models.User
import retrofit2.Response
import retrofit2.http.GET

interface WithTestApi {

    /**
     * FETCH USERS
     */
    @GET("main/api/users/users.json")
    suspend fun getUsers(): Response<List<User>>

}