package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
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
        val api = ApiHelper.getInstance().create(ApiService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val res = api.getCovidInfo().body()
            withContext(Dispatchers.Main){
                findViewById<TextView>(R.id.textView).text = res?.get(0)?.toString()
            }
        }
    }
}
