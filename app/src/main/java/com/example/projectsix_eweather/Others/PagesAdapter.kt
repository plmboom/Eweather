package com.example.projectsix_eweather.Others

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.projectsix_eweather.Activity.MainFragment
import com.example.projectsix_eweather.Activity.SearchCityFragment
import com.example.projectsix_eweather.Activity.SettingsFragment

class PagesAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MainFragment()
            1 -> SearchCityFragment()
            else -> SettingsFragment()

        }
    }

}