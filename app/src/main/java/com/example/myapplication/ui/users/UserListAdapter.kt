package com.example.myapplication.ui.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplication.data.models.User
import com.example.myapplication.databinding.ItemUserBinding

class UserListAdapter(
    val users: List<User>
) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

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