package com.example.projectsix_eweather.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.projectsix_eweather.R
import com.example.projectsix_eweather.Api.Weather.RetrofitClient
import com.example.projectsix_eweather.Others.SearchCityHistory
import com.example.projectsix_eweather.Api.City.UnsplashRetrofitClient
import com.example.projectsix_eweather.Api.Weather.WeatherResponse
import com.example.projectsix_eweather.UnsplashResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchCityActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.search_city)

        SearchCityHistory.loadIntoViews(this)

        val editTextCity: TextView = findViewById(R.id.editTextCity)
        val buttonSelect: Button = findViewById(R.id.buttonSelect)
        val buttonLeft: Button = findViewById(R.id.buttonLeft)
        val buttonRight: Button = findViewById(R.id.buttonRight)

        buttonLeft.setOnClickListener {
            if (this !is MainActivity) {
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                startActivity(intent)
            }
        }

        buttonRight.setOnClickListener {
            if (this !is SettingsActivity) {
                val intent = Intent(this, SettingsActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                startActivity(intent)
            }
        }

        buttonSelect.setOnClickListener {
            val city = editTextCity.text.toString()


            lifecycleScope.launch {
                try {
                val weather = RetrofitClient.weatherApi.getCurrentWeather(
                    apiKey = "3b10b1cba38b4e4cbd7121115262306",
                    city = city
                )

                val photoUrl = try{ UnsplashRetrofitClient.unsplashApi.searchPhotos(
                    query = weather.location.name,
                    clientId = "gL4OTNILQ327ml14dWFYIa8ZYqYAtXrsuSQv143yAROUSGmnPuRKu6iL"
                ).photos?.firstOrNull()?.src?.large
            } catch (e: Exception){
                null
            }


                SearchCityHistory.saveCity(
                    this@SearchCityActivity,
                    weather.location.name,
                    weather.current.temp_c,
                    weather.current.condition.text,
                    weather.current.condition.code,
                    weather.location.localtime
                )




                    val intent = Intent()
                    intent.putExtra("city", weather.location.name)
                    intent.putExtra("temp", weather.current.temp_c)
                    intent.putExtra("feelsLikeTemp", weather.current.feelslike_c)
                    intent.putExtra("text", weather.current.condition.text)
                    intent.putExtra("code", weather.current.condition.code)
                    intent.putExtra("todayDate", weather.location.localtime)
                    intent.putExtra("wind_dir", weather.current.wind_dir)
                    intent.putExtra("wind_kph", weather.current.wind_kph)
                    intent.putExtra("wind_degree", weather.current.wind_degree)
                    intent.putExtra("uv", weather.current.uv)
                    setResult(RESULT_OK, intent)
                    finish()
                }


               catch (e: Exception) {
                    Toast.makeText(
                        this@SearchCityActivity,
                        "Ошибка ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}