package com.example.myapplication.ui.users

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.data.models.User
import com.example.myapplication.ui.users.vo.UserAdapter
import com.example.myapplication.utils.compose.MyAppTheme

@Composable
fun UserScreen(
    uiState: UserListUiState,
    action: (UserListAction) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

        },
    ) { values ->
        when (val adapter = uiState.userAdapter) {
            UserAdapter.Initial,
            UserAdapter.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(values),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is UserAdapter.Success -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize().padding(values),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(16.dp),
                ) {
                    items(adapter.users) { user ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colors.background)
                                .padding(4.dp)
                        ) {
                            AsyncImage(
                                modifier = Modifier.aspectRatio(1f, true),
                                model = user.photo,
                                error = painterResource(id = R.drawable.ic_launcher_foreground),
                                fallback = painterResource(id = R.drawable.ic_launcher_foreground),
                                placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                            Text(
                                modifier = Modifier.padding(top = 4.dp),
                                text = user.nickname,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1
                            )
                        }
                    }
                }
            }
            is UserAdapter.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize().padding(values),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = adapter.message ?: stringResource(id = R.string.network_error),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    Button(
                        shape = RoundedCornerShape(50),
                        onClick = {
                            action(UserListAction.Retry)
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.retry),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun UserScreenPreview() {
    MyAppTheme {
        UserScreen(
            uiState = UserListUiState(
                userAdapter = UserAdapter.Success(
                    users = listOf(
                        User(
                            id = 1,
                            nickname = "Nickname 1",
                            photo = ""
                        ),
                        User(
                            id = 2,
                            nickname = "Nickname 2",
                            photo = ""
                        ),
                        User(
                            id = 3,
                            nickname = "Nickname 3",
                            photo = ""
                        ),
                        User(
                            id = 4,
                            nickname = "Nickname 4",
                            photo = ""
                        )
                    )
                )
            ),
            action = {}
        )
    }
}

@Preview
@Composable
fun UserScreenLoadingPreview() {
    MyAppTheme {
        UserScreen(
            uiState = UserListUiState(
                userAdapter = UserAdapter.Loading
            ),
            action = {}
        )
    }
}

@Preview
@Composable
fun UserScreenErrorPreview() {
    MyAppTheme {
        UserScreen(
            uiState = UserListUiState(
                userAdapter = UserAdapter.Error(message = "Error messageError")
            ),
            action = {}
        )
    }
}