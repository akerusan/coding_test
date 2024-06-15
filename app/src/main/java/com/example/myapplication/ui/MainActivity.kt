package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplication.data.models.User
import com.example.myapplication.data.useCase.GetUsersUseCase
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.ItemUserBinding
import com.example.myapplication.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var getUsersUseCase: GetUsersUseCase

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        lifecycleScope.launch(Dispatchers.IO) {
            getUsersUseCase().let { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        withContext(Dispatchers.Main) {
                            result.data?.let {
                                binding.recyclerView.adapter = MyAdapter(it)
                            }
                        }
                    }
                    is NetworkResult.Error -> TODO()
                    is NetworkResult.Empty -> TODO()
                    is NetworkResult.Loading -> TODO()
                }
            }
        }
    }
}

class MyAdapter(
    val users: List<User>
) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemUserBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view.root)
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.nicknameTextView.text = users[position].nickname
        holder.binding.thumbnailImageView.load(users[position].photo)
    }
}