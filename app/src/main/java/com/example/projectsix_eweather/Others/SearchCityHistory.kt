package com.example.projectsix_eweather.Others
//история на экране поиска
import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.projectsix_eweather.Activity.SearchCityActivity
import com.example.projectsix_eweather.R

object SearchCityHistory {


    fun saveCity(context: Context, city: String, temp: Double, condition: String, code: Int, time: String, photoUrl: String?=null){
        val prefs = context.getSharedPreferences("weather_prefs", Context.MODE_PRIVATE)
        val editor = prefs.edit()

        editor.putString("history_4_city", prefs.getString("history_3_city", null))
        editor.putFloat("history_4_temp", prefs.getFloat("history_3_temp", 0f))
        editor.putString("history_4_condition", prefs.getString("history_3_condition", null))
        editor.putInt("history_4_code", prefs.getInt("history_3_code", 0))
        editor.putString("history_4_time", prefs.getString("history_3_time", null))
        editor.putString("history_4_photo", prefs.getString("history_3_photo", null))

        editor.putString("history_3_city", prefs.getString("history_2_city", null))
        editor.putFloat("history_3_temp", prefs.getFloat("history_2_temp", 0f))
        editor.putString("history_3_condition", prefs.getString("history_2_condition", null))
        editor.putInt("history_3_code", prefs.getInt("history_2_code", 0))
        editor.putString("history_3_time", prefs.getString("history_2_time", null))
        editor.putString("history_3_photo", prefs.getString("history_2_photo", null))


        editor.putString("history_2_city", prefs.getString("history_1_city", null))
        editor.putFloat("history_2_temp", prefs.getFloat("history_1_temp", 0f))
        editor.putString("history_2_condition", prefs.getString("history_1_condition", null))
        editor.putInt("history_2_code", prefs.getInt("history_1_code", 0))
        editor.putString("history_2_time", prefs.getString("history_1_time", null))
        editor.putString("history_2_photo", prefs.getString("history_1_photo", null))


        editor.putString("history_1_city", city)
        editor.putFloat("history_1_temp", temp.toFloat())
        editor.putString("history_1_condition", condition)
        editor.putInt("history_1_code", code)
        editor.putString("history_1_time", time)
        editor.putString("history_1_photo", photoUrl)

        editor.apply()

    }

    fun loadIntoViews(activity: SearchCityActivity){
        val prefs = activity.getSharedPreferences("weather_prefs", Context.MODE_PRIVATE)
        for (i in 1..4){
            val city = prefs.getString("history_${i}_city", null)
            val temp = prefs.getFloat("history_${i}_temp", 0f)
            val condition = prefs.getString("history_${i}_condition", null)
            //val code = prefs.getInt("history_${i}_code", 0)
            val time = prefs.getString("history_${i}_time", null)
            val photoUrl = prefs.getString("history_${i}_time", null)

            if (city != null){
                val cityView: TextView
                val tempView: TextView
                val conditionView: TextView
                val timeView: TextView
                val photoView: ImageView

                when (i){

                    1-> {
                        cityView = activity.findViewById(R.id.citySearch1)
                        tempView = activity.findViewById(R.id.mainTemperatureSearch1)
                        conditionView = activity.findViewById(R.id.conditionSearch1)
                        timeView = activity.findViewById(R.id.timeSearch1)
                        photoView = activity.findViewById(R.id.photoSearch1)
                    }

                    2 -> {
                        cityView = activity.findViewById(R.id.citySearch2)
                        tempView = activity.findViewById(R.id.mainTemperatureSearch2)
                        conditionView = activity.findViewById(R.id.conditionSearch2)
                        timeView = activity.findViewById(R.id.timeSearch2)
                        photoView = activity.findViewById(R.id.photoSearch2)
                    }

                    3 -> {
                        cityView = activity.findViewById(R.id.citySearch3)
                        tempView = activity.findViewById(R.id.mainTemperatureSearch3)
                        conditionView = activity.findViewById(R.id.conditionSearch3)
                        timeView = activity.findViewById(R.id.timeSearch3)
                        photoView = activity.findViewById(R.id.photoSearch3)
                    }

                    else -> {
                        cityView = activity.findViewById(R.id.citySearch4)
                        tempView = activity.findViewById(R.id.mainTemperatureSearch4)
                        conditionView = activity.findViewById(R.id.conditionSearch4)
                        timeView = activity.findViewById(R.id.timeSearch4)
                        photoView = activity.findViewById(R.id.photoSearch4)
                    }
                }

                cityView.text = city
                tempView.text = "${temp.toInt()}°C"
                conditionView.text = WeatherFormatters.translate(condition!!)
                timeView.text = "${WeatherFormatters.formatDateTimeMini(time!!)}"

                if (photoUrl!=null){
                    Glide.with(activity).load(photoUrl).into(photoView)

                }




            }


            }

        }

    }


