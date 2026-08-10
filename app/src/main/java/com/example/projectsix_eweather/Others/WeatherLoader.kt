package com.example.projectsix_eweather.Others
//ф-ция автоподгрузки данных при запуске

import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.projectsix_eweather.Activity.MainActivity
import com.example.projectsix_eweather.Api.Weather.RetrofitClient
import com.example.projectsix_eweather.Api.Weather.WeatherResponse
import com.example.projectsix_eweather.R
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object WeatherLoader {


    fun loadWeatherForCity(activity: MainActivity, city: String){

        activity.lifecycleScope.launch {
            try {
                val weather = RetrofitClient.weatherApi.getCurrentWeather(
                    apiKey = "3b10b1cba38b4e4cbd7121115262306",
                    city = city
                )

                activity.findViewById<TextView>(R.id.city).text = " ${weather.location.name}"    //findViewById<TextView>(R.id.city).text = "📍 ${weather.location.name}"
                activity.findViewById<TextView>(R.id.mainTemperature).text = "${weather.current.temp_c.toInt()}°C"
                activity.findViewById<TextView>(R.id.feelsLikeTemp).text = "${weather.current.feelslike_c.toInt()}°C"
                activity.findViewById<TextView>(R.id.condition).text = "${WeatherFormatters.emoji(weather.current.condition.code)} ${WeatherFormatters.translate(weather.current.condition.text)}"
                activity.findViewById<TextView>(R.id.todayDate).text = "${WeatherFormatters.formatDateTime(weather.location.localtime)}."
                activity.findViewById<TextView>(R.id.wind_dir).text = "Направление: ${weather.current.wind_dir}"
                activity.findViewById<TextView>(R.id.wind_kph).text = "Скорсоть: ${WeatherFormatters.translateSpeed(weather.current.wind_kph.toInt())} м/с"
                activity.findViewById<ImageView>(R.id.windArrow).rotation = weather.current.wind_degree.toFloat()
                activity.findViewById<TextView>(R.id.uv).text = "${WeatherFormatters.uvNew(weather.current.uv)}"
                activity.findViewById<TextView>(R.id.gradus).text = "${weather.current.wind_degree}°"

            }
            catch (e: Exception) {
                Toast.makeText(activity, "Ошибка загрузки: ${e.message}", Toast.LENGTH_SHORT).show()

            }
        }
    }
}










