package com.example.myapplication.data

import com.example.myapplication.data.models.User
import com.example.myapplication.data.network.WithTestApi
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val withTestApi: WithTestApi) {

    /**
     * FETCH USERS
     */
    suspend fun getUsers(): Response<List<User>> {
        return withTestApi.getUsers()
    }
}