package com.example.coroutines

import com.example.democoroutine.model.CovidInfor
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("v3/stats/worldometer/country?countryCode=VN")
    fun getCovidInfo(): Call<List<CovidInfor>>
}
