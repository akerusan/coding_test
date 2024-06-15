package com.example.myapplication.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.useCase.GetUsersUseCase
import com.example.myapplication.ui.users.vo.UserAdapter
import com.example.myapplication.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
): ViewModel() {

    private var _getUsers: MutableStateFlow<UserAdapter> = MutableStateFlow(UserAdapter.Initial)
    var getUsers = _getUsers.asStateFlow()

    init {
        getUsers()
    }

    private fun getUsers() = viewModelScope.launch {
        getUsersSafeCall()
    }

    private suspend fun getUsersSafeCall() {
        getUsersUseCase().let { result ->
            when (result) {
                is NetworkResult.Success -> {
                    withContext(Dispatchers.Main) {
                        result.data?.let {
                            _getUsers.value = UserAdapter.Success(it)
                        }
                    }
                }
                is NetworkResult.Error-> {
                    _getUsers.value = UserAdapter.Error(message = result.message)
                }
                is NetworkResult.Loading -> {
                    _getUsers.value = UserAdapter.Loading
                }
            }
        }
    }
}