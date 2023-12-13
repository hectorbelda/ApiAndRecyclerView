package com.hbm.apiandrecyclerview

import com.google.gson.annotations.SerializedName

data class DriversResponse(
    @SerializedName("MRData") val mrData: MrData
)

data class MrData(
    @SerializedName("DriverTable") val driverTable: DriverTable
)

data class DriverTable(
    @SerializedName("Drivers") val drivers: List<Driver>
)

data class Driver(
    @SerializedName("driverId") val driverId: String,
    @SerializedName("permanentNumber") val permanentNumber: String,
    @SerializedName("code") val code: String,
    @SerializedName("url") val url: String,
    @SerializedName("givenName") val givenName: String,
    @SerializedName("familyName") val familyName: String,
    @SerializedName("dateOfBirth") val dateOfBirth: String,
    @SerializedName("nationality") val nationality: String
)
{
    fun getFullName(): String {
        return "$givenName $familyName"
    }
}