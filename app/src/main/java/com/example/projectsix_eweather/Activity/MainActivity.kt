package com.example.projectsix_eweather.Activity

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.example.projectsix_eweather.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)



        val prefs = getSharedPreferences("weather_prefs", MODE_PRIVATE)
        val isDark = prefs.getBoolean("dark_theme", false)

        if (isDark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val buttonLeft: Button = findViewById(R.id.buttonLeft)
        val buttonCenter: Button = findViewById(R.id.buttonCenter)
        val buttonRight: Button = findViewById(R.id.buttonRight)

        val navOptions = NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in_right)
            .setExitAnim(R.anim.slide_out_left)
            .setPopEnterAnim(R.anim.slide_in_left)
            .setPopExitAnim(R.anim.slide_out_right)
            .build()

        buttonLeft.setOnClickListener {
            navigateTo(navController, R.id.mainFragment)
        }

        buttonCenter.setOnClickListener {
            navigateTo(navController, R.id.searchCityFragment)
        }

        buttonRight.setOnClickListener {
            navigateTo(navController, R.id.settingsFragment)
        }
    }
    }



    private fun navigateTo(navController: NavController, destination: Int){
        val current = navController.currentDestination?.id
        if (current == destination) return

        val actionId = when(current to destination){
            R.id.mainFragment to R.id.searchCityFragment -> R.id.action_mainFragment_to_searchCityFragment
            R.id.mainFragment to R.id.settingsFragment -> R.id.action_mainFragment_to_settingsFragment

            R.id.searchCityFragment to R.id.mainFragment -> R.id.action_searchCityFragment_to_mainFragment
            R.id.searchCityFragment to R.id.settingsFragment -> R.id.action_searchCityFragment_to_settingsFragment

            R.id.settingsFragment to R.id.mainFragment -> R.id.action_settingsFragment_to_mainFragment
            R.id.settingsFragment to R.id.searchCityFragment -> R.id.action_settingsFragment_to_searchCityFragment

            else -> null
        }

        if (actionId != null){
            navController.navigate(actionId)
        }


    }


