package com.hbm.apiandrecyclerview

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DriverDetailActivity : AppCompatActivity() {

    private lateinit var nameTextView: TextView
    private lateinit var numberTextView: TextView
    private lateinit var nationalityTextView: TextView
    private lateinit var dobTextView: TextView
    private lateinit var bioTextView: TextView
    private lateinit var codTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_detail)

        val driver: Driver? = intent.getParcelableExtra("driver")

        if (driver != null) {
            nameTextView = findViewById(R.id.detailNameTextView)
            numberTextView = findViewById(R.id.detailNumberTextView)
            nationalityTextView = findViewById(R.id.detailNationalityTextView)
            dobTextView = findViewById(R.id.detailDobTextView)
            bioTextView = findViewById(R.id.detailBioTextView)
            codTextView = findViewById(R.id.codTextView)

            nameTextView.text = "${driver.givenName} ${driver.familyName}"
            codTextView.text = "Abreviatura: ${driver.code}"
            numberTextView.text = "Número: ${driver.permanentNumber}"
            nationalityTextView.text = "Nacionalidad: ${driver.nationality}"
            dobTextView.text = "Fecha de nacimiento: ${driver.dateOfBirth}"
            bioTextView.text = "Conoce más sobre el piloto: \n${driver.url}"
        }
    }
}

