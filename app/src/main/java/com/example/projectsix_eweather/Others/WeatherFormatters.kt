package com.example.projectsix_eweather.Others
//Функции перевода
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.roundToInt

object WeatherFormatters {

    fun emoji(code: Int): String {

        return when (code) {
            1000 -> "☀️"
            1003 -> "☁️"
            1006 -> "☁️"
            1009 -> "☁️"
            1030 -> "🌫️"
            1063, 1153, 1150, in 1180..1201 -> "🌧️"
            1066, in 1210..1225 -> "❄️"
            1087, in 1273..1282 -> "⛈️"
            1135, 1147 -> "🌫️"
            else -> "Err"
        }
    }


    fun translate(text: String): String{

        return when (text){
            "Sunny" -> "Солнечно"
            "Clear" -> "Ясно"
            "Partly cloudy" -> "Переменная облачность"
            "Cloudy" -> "Облачно"
            "Overcast" -> "Пасмурно"
            "Mist" -> "Дымка"
            "Rain" -> "Дождь"
            "Snow" -> "Снег"
            "Thunder" -> "Гром"
            "Fog" -> "Туман"
            "Patchy rain nearby", "Patchy light rain in area with thunder" -> "Небольшой дождь рядом"
            "Moderate or heavy rain with thunder" -> "Сильный дождь с грозой"
            "Light rain" -> "Небольшой дождь"
            "Thundery outbreaks in nearby" -> "Гроза в пределах 50 километров"
            "Light drizzle" -> "Легкий дождь"
            "Partly Cloudy" -> "Переменная облачность"
            "Patchy light drizzle" -> "Легкий дождь"

            else -> "Err"
        }

    }
    fun formatDateTime(raw: String): String{
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale("ru"))
        val outputFormat = SimpleDateFormat("EEE, d MMMM, HH:mm", Locale("ru"))
        val date = inputFormat.parse(raw)
        val formatted = outputFormat.format(date!!)
        return formatted.replaceFirstChar {
            it.uppercase()
        }
    }

    fun formatDateTimeMini(raw: String): String{
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale("ru"))
        val outputFormat = SimpleDateFormat("HH:mm", Locale("ru"))
        val date = inputFormat.parse(raw)
        val formatted = outputFormat.format(date!!)
        return formatted.replaceFirstChar {
            it.uppercase()

        }


    }


    fun translateSpeed(wind_kph: Int): Int{

        var wind_kph = wind_kph * 1000 / 3600
        return wind_kph
    }

    fun uvNew(uv: Double): Int{
        return if (uv<1.0){
            (uv*10).roundToInt()
        }
        else{
            uv.roundToInt()}
    }









}