package com.example.myapplication.ui.users

import com.example.myapplication.ui.users.vo.UserAdapter

data class UserListUiState(
    val userAdapter: UserAdapter = UserAdapter.Initial
)