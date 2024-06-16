package com.example.myapplication.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Repository
import com.example.myapplication.data.database.entities.UserEntity
import com.example.myapplication.data.useCase.GetUsersUseCase
import com.example.myapplication.ui.users.vo.UserAdapter
import com.example.myapplication.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val repository: Repository,
    private val getUsersUseCase: GetUsersUseCase
): ViewModel() {

    private val _usersFromDb = MutableStateFlow<UserAdapter>(UserAdapter.Initial)
    val usersFromDb: StateFlow<UserAdapter> = _usersFromDb.asStateFlow()

    init {
        fetchUsers()
        getUsersFromDb()
    }

    val uiState: StateFlow<UserListUiState> = usersFromDb.map { userAdapter ->
        UserListUiState(userAdapter)
    }.stateIn(scope = viewModelScope, started = SharingStarted.Eagerly, initialValue = UserListUiState())

    /** LOCAL DB */

    private fun getUsersFromDb() = viewModelScope.launch {
        repository.local.getUsers().collect { db ->
            if (db.isNotEmpty()) {
                _usersFromDb.value = UserAdapter.Success(db[0].user)
            }
        }
    }

    private fun insertUsers(userEntity: UserEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertUsers(userEntity)
        }

    /** REMOTE */

    private fun fetchUsers() = viewModelScope.launch(Dispatchers.IO) {
        _usersFromDb.value = UserAdapter.Loading
        getUsersUseCase().let { result ->
            when (result) {
                is NetworkResult.Success -> {
                    result.data?.let {
                        insertUsers(UserEntity(it))
                    }
                }
                is NetworkResult.Error-> {
                    _usersFromDb.value = UserAdapter.Error(message = result.message)
                }
                is NetworkResult.Loading -> {
                    _usersFromDb.value = UserAdapter.Loading
                }
            }
        }
    }

    fun dispatch(action: UserListAction) {
        when (action) {
            is UserListAction.Retry -> fetchUsers()
        }
    }
}