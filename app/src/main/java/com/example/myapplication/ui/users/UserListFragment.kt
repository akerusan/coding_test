package com.example.myapplication.ui.users

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.FragmentUserListBinding
import com.example.myapplication.ui.users.vo.UserAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private val viewModel: UserListViewModel by viewModels()

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =  FragmentUserListBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            viewModel.getUsers.collectLatest {
                when (it) {
                    UserAdapter.Initial,
                    UserAdapter.Loading -> {
                        // Show loading
                    }
                    is UserAdapter.Success -> {
                        val mAdapter = UserListAdapter(it.data)
                        binding.recyclerView.apply {
                            adapter = mAdapter
                        }
                    }
                    is UserAdapter.Error -> {
                        // Show Error
                    }
                }
            }
        }

        return binding.root
    }
}