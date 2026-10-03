package com.example.projectsix_eweather.Activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.projectsix_eweather.R

class SettingsFragment: Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val whiteTheme = view.findViewById<RadioButton>(R.id.whiteTheme)
        val blackTheme = view.findViewById<RadioButton>(R.id.blackTheme)

        val prefs = requireContext().getSharedPreferences("weather_prefs", Context.MODE_PRIVATE)
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
    }
}