package com.mml.newsapp.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.androiddevs.mvvmnewsapp.repository.NewsRepository
import com.mml.newsapp.R
import com.mml.newsapp.databinding.ActivityMainBinding
import com.mml.newsapp.db.ArticleDatabase
import com.mml.newsapp.viewmodels.NewsViewModel
import com.mml.newsapp.viewmodels.NewsViewModelProviderFactory

class MainActivity : AppCompatActivity() {


    companion object {
        private const val TAG = "MainActivity"
    }


    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) { // Q  API 29
            enableEdgeToEdge()
            Log.d(TAG, "Edge-to-Edge enabled for API ${Build.VERSION.SDK_INT}")


        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootView)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



      

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) { // Q  API 29
            binding.bottomNavigationView.itemIconTintList =
                ContextCompat.getColorStateList(this, R.color.bottom_navigation_selector)
            binding.bottomNavigationView.itemTextColor =
                ContextCompat.getColorStateList(this, R.color.bottom_navigation_selector)
        }


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController



        binding.bottomNavigationView.setupWithNavController(navController)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(application, newsRepository)

        viewModel = ViewModelProvider(this, viewModelProviderFactory)[NewsViewModel::class.java]

    }
}