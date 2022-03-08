package com.example.coroutines

import com.example.democoroutine.model.CovidInfor
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("v3/stats/worldometer/country?countryCode=VN")
    suspend fun getCovidInfo(): Response<List<CovidInfor>>
}
