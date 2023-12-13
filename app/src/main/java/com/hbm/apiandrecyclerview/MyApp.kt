package com.hbm.apiandrecyclerview

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApp : Application() {

    companion object {
        lateinit var apiService: ApiService
    }

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://ergast.com/api/f1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        apiService = retrofit.create(ApiService::class.java)
    }
}
