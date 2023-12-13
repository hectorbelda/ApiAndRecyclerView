package com.hbm.apiandrecyclerview

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.hbm.apiandrecyclerview.Driver

class DriverDetailActivity : AppCompatActivity() {

    private lateinit var nameTextView: TextView
    private lateinit var numberTextView: TextView
    private lateinit var nationalityTextView: TextView
    private lateinit var dobTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_detail)

        // Obtén los datos del piloto del intento
        val driver: Driver? = intent.getParcelableExtra("driver")

        // Configura la interfaz de usuario con los detalles del piloto
        if (driver != null) {
            nameTextView = findViewById(R.id.detailNameTextView)
            numberTextView = findViewById(R.id.detailNumberTextView)
            nationalityTextView = findViewById(R.id.detailNationalityTextView)
            dobTextView = findViewById(R.id.detailDobTextView)

            nameTextView.text = "${driver.givenName} ${driver.familyName}"
            numberTextView.text = "Number: ${driver.permanentNumber}"
            nationalityTextView.text = "Nationality: ${driver.nationality}"
            dobTextView.text = "DOB: ${driver.dateOfBirth}"
        }
    }
}

