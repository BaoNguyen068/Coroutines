package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.democoroutine.model.CovidInfor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CoroutineScope(Dispatchers.IO).launch {
            var list = getCovid()
            withContext(Dispatchers.Main){
                findViewById<TextView>(R.id.textView).text = list.toString()
            }
        }
    }
    suspend fun getCovid(): List<CovidInfor> {
        var list: List<CovidInfor> = listOf()
        try {
            list = Retrofit.Builder()
                .baseUrl("https://api.coronatracker.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java).getCovidInfo().execute().body() ?: listOf()
             Log.e("test", list.size.toString())
        }catch (e:Exception){
            e.printStackTrace()
        }
        return list
    }
}