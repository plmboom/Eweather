package com.example.projectsix_eweather.Activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.projectsix_eweather.Others.SearchCityHistory
import com.example.projectsix_eweather.OthersH.WeatherViewModel
import com.example.projectsix_eweather.R
import kotlinx.coroutines.launch
import kotlin.getValue
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchCityFragment: Fragment() {

    private val viewModel: WeatherViewModel by activityViewModels()
    private var inScreenS = false
    private var shouldSaveHistory = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_city, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        SearchCityHistory.loadIntoViews(this)

        val plateIds = listOf(R.id.PlateSearch1, R.id.PlateSearch2, R.id.PlateSearch3, R.id.PlateSearch4)
        val cityIds = listOf(R.id.citySearch1, R.id.citySearch2, R.id.citySearch3, R.id.citySearch4)

        val editTextCity: TextView = view.findViewById(R.id.editTextCity)
        val buttonSelect: Button = view.findViewById(R.id.buttonSelect)
        val progressBar: ProgressBar = view.findViewById(R.id.progressBar)

        for (i in plateIds.indices) {
            val plate = view.findViewById<View>(plateIds[i])
            val cityView = view.findViewById<TextView>(cityIds[i])

            plate.setOnClickListener {
                val city = cityView.text.toString()

                if (city.isNotBlank()) {
                    progressBar.visibility = View.VISIBLE
                    buttonSelect.isEnabled = false
                    inScreenS = true
                    shouldSaveHistory = false
                    viewModel.loadWeatherForCity(city)
                    viewModel.loadPhotoCity(city)
                }
            }
        }



        viewModel.error.observe(viewLifecycleOwner) { message ->
            progressBar.visibility = View.GONE
            progressBar.isEnabled = true
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }


        viewModel.weather.observe(viewLifecycleOwner) { weather ->
            if (!inScreenS) return@observe
            inScreenS = false
            progressBar.visibility = View.GONE
            progressBar.isEnabled = true

            viewLifecycleOwner.lifecycleScope.launch {
                if (shouldSaveHistory) {
                    val photoUrl = viewModel.fetchCityPhotoUrl(weather.location.name)

                    SearchCityHistory.saveCity(
                        requireContext(),
                        weather.location.name,
                        weather.current.temp_c,
                        weather.current.condition.text,
                        weather.current.condition.code,
                        weather.location.localtime,
                        photoUrl
                    )
                }

                    requireContext().getSharedPreferences("weather_prefs", Context.MODE_PRIVATE)
                        .edit()
                        .putString("last_city", weather.location.name)
                        .apply()
                findNavController().popBackStack(R.id.mainFragment, false)
            }
        }
            buttonSelect.setOnClickListener {
                val city = editTextCity.text.toString()
                progressBar.visibility = View.VISIBLE
                buttonSelect.isEnabled = false
                inScreenS = true
                shouldSaveHistory = true
                viewModel.loadWeatherForCity(city)
            }
        }
    }




