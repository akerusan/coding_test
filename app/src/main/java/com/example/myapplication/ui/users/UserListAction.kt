package com.example.myapplication.ui.users

sealed interface UserListAction {
    data object Retry : UserListAction
}