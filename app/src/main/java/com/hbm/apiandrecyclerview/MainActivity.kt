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

        // Aquí cambiamos la creación del adaptador para incluir la lambda onItemClick
        driverAdapter = DriverAdapter(driversList) { selectedDriver ->
            val intent = Intent(this, DriverDetailActivity::class.java)
            intent.putExtra("driver", selectedDriver)
            startActivity(intent)
        }
        recyclerView.adapter = driverAdapter

        fetchData()
    }

    private fun filterDrivers(query: String?) {
        // Filtrar la lista de pilotos según el texto de búsqueda
        val filteredList = driversList.filter {
            it.givenName.contains(query.orEmpty(), ignoreCase = true) ||
                    it.familyName.contains(query.orEmpty(), ignoreCase = true) ||
                    it.nationality.contains(query.orEmpty(), ignoreCase = true) ||
                    it.dateOfBirth.contains(query.orEmpty(), ignoreCase = true) ||
                    it.permanentNumber.contains(query.orEmpty(), ignoreCase = true)
        }

        // Actualizar el adaptador con la lista filtrada
        driverAdapter.filterList(filteredList)
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

                    // Actualizar el RecyclerView en el hilo principal
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







