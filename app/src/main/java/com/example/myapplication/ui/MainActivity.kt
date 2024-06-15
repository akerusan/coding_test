package com.example.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController

    }
}