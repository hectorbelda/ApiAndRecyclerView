package com.hbm.apiandrecyclerview

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("2023/drivers.json")
    suspend fun getDrivers(): Response<DriversResponse>
}

