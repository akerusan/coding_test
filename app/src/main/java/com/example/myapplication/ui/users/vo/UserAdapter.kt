package com.example.myapplication.ui.users.vo

import com.example.myapplication.data.models.User

sealed class UserAdapter {
    data object Initial : UserAdapter()
    data object Loading : UserAdapter()
    class Success(val users: List<User>): UserAdapter()
    class Error(val message: String?): UserAdapter()
}