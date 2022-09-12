package com.lukic.movieapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.lukic.movieapp.databinding.ActivityMainBinding
import com.lukic.movieapp.ui.DetailsFragment

const val MOVIE_ID_KEY = "movieId"

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

        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<Int>(MOVIE_ID_KEY)
            ?.observe(this) { id ->
                setDetailsFragment(id)
                showBackButton()
                binding.mainContainerWithBottomNav.visibility = View.GONE
                binding.mainFragmentcontainerDetails.visibility = View.VISIBLE
            }

        binding.mainBack.setOnClickListener(backButtonClickListener())
        binding.mainBottomNavigation.setupWithNavController(navController)
    }

    private fun setDetailsFragment(id: Int) {
        val bundle = Bundle()
        bundle.putInt("movieId", id)
        val detailsFragment = DetailsFragment()
        detailsFragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragmentcontainer_details, detailsFragment)
            .commit()
    }

    private fun backButtonClickListener() = View.OnClickListener {
        binding.mainContainerWithBottomNav.visibility = View.VISIBLE
        binding.mainFragmentcontainerDetails.visibility = View.GONE
        hideBackButton()
    }

    private fun showBackButton() {
        binding.mainBack.visibility = View.VISIBLE
    }

    private fun hideBackButton() {
        binding.mainBack.visibility = View.GONE
    }

    override fun onBackPressed() {
        if (binding.mainFragmentcontainerDetails.visibility == View.VISIBLE) {
            backButtonClickListener().onClick(binding.root)
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()
}
