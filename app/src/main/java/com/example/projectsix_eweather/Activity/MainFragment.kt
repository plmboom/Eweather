package com.example.projectsix_eweather.Activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.projectsix_eweather.Others.WeatherFormatters
import com.example.projectsix_eweather.OthersH.WeatherViewModel
import com.example.projectsix_eweather.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment: Fragment() {

    private val viewModel: WeatherViewModel by activityViewModels ()

    private var firstLoad = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    private fun animPlate2(plate: View, delay: Long){
        plate.alpha = 0f
        plate.translationY = 50f
        plate.animate()
            .alpha(1f)
            .translationY(0f)
            .setDuration(500)
            .setStartDelay(delay)
            .start()

    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.error.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }



        viewModel.weather.observe(viewLifecycleOwner) { weather ->
            view.findViewById<TextView>(R.id.city).text = " ${weather.location.name}"
            view.findViewById<TextView>(R.id.mainTemperature).text = "${weather.current.temp_c.toInt()}°C"
            view.findViewById<TextView>(R.id.feelsLikeTemp).text = "${weather.current.feelslike_c.toInt()}°C"
            view.findViewById<TextView>(R.id.condition).text = "${WeatherFormatters.emoji(weather.current.condition.code)} ${WeatherFormatters.translate(weather.current.condition.text)}"
            view.findViewById<TextView>(R.id.todayDate).text = "${WeatherFormatters.formatDateTime(weather.location.localtime)}."
            view.findViewById<TextView>(R.id.wind_dir).text = "Направление: ${weather.current.wind_dir}"
            view.findViewById<TextView>(R.id.wind_kph).text = "Скорсоть: ${WeatherFormatters.translateSpeed(weather.current.wind_kph.toInt())} м/с"
            view.findViewById<ImageView>(R.id.windArrow).rotation = weather.current.wind_degree.toFloat()
            view.findViewById<TextView>(R.id.uv).text = "${WeatherFormatters.uvNew(weather.current.uv)}"
            view.findViewById<TextView>(R.id.gradus).text = "${weather.current.wind_degree}°"

            val mainPlate: View = view.findViewById(R.id.mainPlate)
            val leftPlate: View = view.findViewById(R.id.leftPlate)
            val rightPlate: View = view.findViewById(R.id.rightPlate)
            val secondBigPlate: View = view.findViewById(R.id.secondBigPlate)

            animPlate2(mainPlate, 0)
            animPlate2(leftPlate, 100)
            animPlate2(rightPlate, 200)
            animPlate2(secondBigPlate, 300)
        }


        if (firstLoad) {
            firstLoad = false
            val prefs =
                requireContext().getSharedPreferences("weather_prefs", Context.MODE_PRIVATE)
            val lastCity = prefs.getString("last_city", null)
            if (lastCity != null) {
                viewModel.loadWeatherForCity(lastCity)
            }
        }
    }
}
