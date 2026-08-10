package com.example.projectsix_eweather.Others

//принимает данные из поиска
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import com.example.projectsix_eweather.Activity.MainActivity
import com.example.projectsix_eweather.R

object WeatherResultHandler {

    fun handleSearchResult(activity: MainActivity, data: Intent?){

        val city = data?.getStringExtra("city")
        val prefs = activity.getSharedPreferences("weather_prefs", MODE_PRIVATE)
        prefs.edit().putString("last_city", city).apply()
        val temp = data?.getDoubleExtra("temp", 0.0)
        val feelsLikeTemp = data?.getDoubleExtra("feelsLikeTemp", 0.0)
        val text = data?.getStringExtra("text")
        val code = data?.getIntExtra("code", 0)
        val todayDate = data?.getStringExtra("todayDate")
        val wind_dir = data?.getStringExtra("wind_dir")
        val wind_kph = data?.getDoubleExtra("wind_kph", 0.0)
        val wind_degree = data?.getIntExtra("wind_degree", 0)
        val uv = data?.getDoubleExtra("uv", 0.0)


        activity.findViewById<TextView>(R.id.city).text = "$city" //findViewById<TextView>(R.id.city).text = "📍 $city"
        activity.findViewById<TextView>(R.id.mainTemperature).text = "${temp?.toInt()}°C"
        activity.findViewById<TextView>(R.id.feelsLikeTemp).text = "${feelsLikeTemp?.toInt()}°C"
        activity.findViewById<TextView>(R.id.condition).text = "${WeatherFormatters.emoji(code!!)} ${WeatherFormatters.translate(text!!)}"
        activity.findViewById<TextView>(R.id.todayDate).text = "${WeatherFormatters.formatDateTime(todayDate!!)}."
        activity.findViewById<TextView>(R.id.wind_dir).text = "Направление: ${wind_dir}"
        activity.findViewById<TextView>(R.id.wind_kph).text = "Скорсоть: ${WeatherFormatters.translateSpeed(wind_kph!!.toInt())} м/с"
        activity.findViewById<ImageView>(R.id.windArrow).rotation = wind_degree!!.toFloat()
        activity.findViewById<TextView>(R.id.uv).text = "${WeatherFormatters.uvNew(uv!!)}"
        activity.findViewById<TextView>(R.id.gradus).text = "${wind_degree}°"



    }


}