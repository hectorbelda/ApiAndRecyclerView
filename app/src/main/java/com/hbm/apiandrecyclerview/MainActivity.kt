package com.hbm.apiandrecyclerview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var driverAdapter: DriverAdapter
    private var driversList: MutableList<Driver> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        driverAdapter = DriverAdapter(driversList) { selectedDriver ->
            val intent = Intent(this, DriverDetailActivity::class.java)
            intent.putExtra("driver", selectedDriver)
            startActivity(intent)
        }
        recyclerView.adapter = driverAdapter

        fetchData()
    }

    private fun fetchData() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = withContext(Dispatchers.IO) {
                    MyApp.apiService.getDrivers()
                }

                if (response.isSuccessful) {
                    val driversResponse = response.body()
                    driversList.addAll(driversResponse?.mrData?.driverTable?.drivers ?: emptyList())

                    driverAdapter.notifyDataSetChanged()
                } else {
                    Log.e("MainActivity", "Error fetching data: ${response.message()}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("MainActivity", "Error fetching data: ${e.message}")
            }
        }
    }
}







