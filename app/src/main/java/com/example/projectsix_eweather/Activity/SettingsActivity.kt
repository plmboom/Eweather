package com.example.projectsix_eweather.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.projectsix_eweather.R

class SettingsActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.settings)


        val buttonCenter: Button = findViewById(R.id.buttonCenter)
        val buttonLeft: Button = findViewById(R.id.buttonLeft)
        val buttonRight: Button = findViewById(R.id.buttonRight)
        val whiteTheme = findViewById<RadioButton>(R.id.whiteTheme)
        val blackTheme = findViewById<RadioButton>(R.id.blackTheme)




        val prefs = getSharedPreferences("weather_prefs", MODE_PRIVATE)
        val isDark = prefs.getBoolean("dark_theme", false)
        if (isDark) blackTheme.isChecked = true else whiteTheme.isChecked = true

        if (isDark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }







        whiteTheme.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            prefs.edit().putBoolean("dark_theme", false).apply()
        }

        blackTheme.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            prefs.edit().putBoolean("dark_theme", true).apply()
        }







        buttonCenter.setOnClickListener {
            if (this !is SearchCityActivity) {
                val intent = Intent(this, SearchCityActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                startActivity(intent)
            }
        }


        buttonLeft.setOnClickListener {
            if (this !is MainActivity) {
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                startActivity(intent)

            }
        }

    }
}