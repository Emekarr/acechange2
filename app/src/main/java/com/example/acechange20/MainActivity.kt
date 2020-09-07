package com.example.acechange20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.acechange20.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        //set up toolbar
        setSupportActionBar(binding.toolBar)

        //set up navigation with bottom nav
        val navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupWithNavController(binding.bottomNavView, navController)
    }
}