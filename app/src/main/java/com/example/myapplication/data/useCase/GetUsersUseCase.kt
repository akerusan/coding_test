package com.example.myapplication.data.useCase

import com.example.myapplication.data.Repository
import com.example.myapplication.data.models.User
import com.example.myapplication.utils.NetworkResult
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke() : NetworkResult<List<User>> {
        try {
            val response = repository.remote.getUsers()
            return when {
                response.isSuccessful -> {
                    NetworkResult.Success(response.body())
                }
                response.message().toString().contains("timeout") -> {
                    NetworkResult.Error(message = "Timeout")
                }
                response.body() == null -> {
                    NetworkResult.Error(message = "Get users body response is null")
                }
                else -> {
                    NetworkResult.Error(message = response.message())
                }
            }
        } catch (e: Exception) {
            return NetworkResult.Error(message = e.message)
        }
    }
}