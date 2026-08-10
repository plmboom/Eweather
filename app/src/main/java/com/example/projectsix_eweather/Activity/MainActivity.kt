package com.example.projectsix_eweather.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.projectsix_eweather.R
import com.example.projectsix_eweather.Others.WeatherLoader
import com.example.projectsix_eweather.Others.WeatherResultHandler

class MainActivity : AppCompatActivity() {

    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
        if (result.resultCode == RESULT_OK) {
            WeatherResultHandler.handleSearchResult(this, result.data)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
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


        val lastCity = prefs.getString("last_city", null)

        if (lastCity != null) {
            WeatherLoader.loadWeatherForCity(this, lastCity)
        }

        val buttonCenter: Button = findViewById(R.id.buttonCenter)
        //val buttonLeft: Button = findViewById(R.id.buttonLeft)
        val buttonRight: Button = findViewById(R.id.buttonRight)


        /*
        val mainTemperature: TextView = findViewById(R.id.mainTemperature)
        val city: TextView = findViewById(R.id.city)
        val feelsLikeTemp = findViewById<TextView>(R.id.feelsLikeTemp)
        val todayDate = findViewById<TextView>(R.id.todayDate)
        val wind_dir = findViewById<TextView>(R.id.wind_dir)
        val wind_kph = findViewById<TextView>(R.id.wind_kph)
        val uv = findViewById<TextView>(R.id.uv)
        val windArrow = findViewById<ImageView>(R.id.windArrow)
        val gradus = findViewById<TextView>(R.id.gradus)
            */

        buttonCenter.setOnClickListener {
            if (this !is SearchCityActivity) {
                val intent = Intent(this, SearchCityActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                launcher.launch(intent)

            }}

        /*buttonLeft.setOnClickListener {
            if (this !is MainActivity) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }
        }*/
        buttonRight.setOnClickListener {
            if (this !is SettingsActivity) {
                val intent = Intent(this, SettingsActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                startActivity(intent)

            }

        }
    }
}