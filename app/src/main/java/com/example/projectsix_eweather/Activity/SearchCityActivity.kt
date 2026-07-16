package com.example.projectsix_eweather.Activity
//Экран поиска
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.projectsix_eweather.R
import com.example.projectsix_eweather.Api.RetrofitClient
import com.example.projectsix_eweather.Others.SearchCityHistory
import com.example.projectsix_eweather.Api.UnsplashRetrofitClient
import com.example.projectsix_eweather.Api.WeatherResponse
import com.example.projectsix_eweather.UnsplashResponse
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

        //val buttonCenter: Button = findViewById(R.id.buttonCenter)
        val buttonLeft: Button = findViewById(R.id.buttonLeft)
        val buttonRight: Button = findViewById(R.id.buttonRight)

        /*val citySearch1 = findViewById<TextView>(R.id.citySearch1)
        val timeSearch1 = findViewById<TextView>(R.id.timeSearch1)
        val conditionSearch1 = findViewById<TextView>(R.id.conditionSearch1)
        val mainTemperatureSearch1 = findViewById<TextView>(R.id.mainTemperatureSearch1)
*/




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




            RetrofitClient.weatherApi.getCurrentWeather(
                apiKey =  "Сюда вставьте ваш API 1",
                city = city
            ).enqueue(object : Callback<WeatherResponse> {

                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                    val weather = response.body()
                    if (weather != null) {

                        UnsplashRetrofitClient.unsplashApi.searchPhotos(
                            query = weather.location.name,
                            clientId = "Сюда вставьте ваш API 2"
                        ).enqueue(object : Callback<UnsplashResponse> {
                            override fun onResponse(
                                call: Call<UnsplashResponse>,
                                response: Response<UnsplashResponse>
                            )

                            {
                                val photoUrl = response.body()?.photos?.firstOrNull()?.src?.large

                                SearchCityHistory.saveCity(
                                    this@SearchCityActivity,
                                    weather.location.name,
                                    weather.current.temp_c,
                                    weather.current.condition.text,
                                    weather.current.condition.code,
                                    weather.location.localtime,
                                    photoUrl
                                )
                            }

                            override fun onFailure(call: Call<UnsplashResponse>, t: Throwable) {
                                SearchCityHistory.saveCity(
                                    this@SearchCityActivity,
                                    weather.location.name,
                                    weather.current.temp_c,
                                    weather.current.condition.text,
                                    weather.current.condition.code,
                                    weather.location.localtime,
                                    null
                                )


                            }
                        }
                        )


                        val intent = Intent()
                        intent.putExtra("city", weather.location.name)
                        intent.putExtra("temp", weather.current.temp_c)
                        intent.putExtra("feelsLikeTemp", weather.current.feelslike_c)
                        intent.putExtra("text", weather.current.condition.text)
                        intent.putExtra("code", weather.current.condition.code)
                        intent.putExtra("todayDate", weather.location.localtime)
                        intent.putExtra("wind_dir",weather.current.wind_dir)
                        intent.putExtra("wind_kph", weather.current.wind_kph)
                        intent.putExtra("wind_degree", weather.current.wind_degree)
                        intent.putExtra("uv", weather.current.uv)
                        setResult(RESULT_OK, intent)
                        finish()

                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Toast.makeText(this@SearchCityActivity, "Ошибка ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}