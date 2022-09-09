package com.lukic.movieapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.lukic.movieapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_fragmentcontainer) as NavHostFragment
        navController = navHostFragment.navController

        binding.mainBack.setOnClickListener {
            navController.navigateUp()
        }

        binding.mainBottomNavigation.setupWithNavController(navController)
    }

    fun showBackButton() {
        binding.mainBack.visibility = View.VISIBLE
    }

    fun hideBackButton() {
        binding.mainBack.visibility = View.GONE
    }

    fun hideBottomNav() {
        binding.mainBottomNavigation.visibility = View.GONE
    }

    fun showBottomNav() {
        binding.mainBottomNavigation.visibility = View.VISIBLE
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()
}
