package com.hbm.apiandrecyclerview

import android.os.Parcel
import android.os.Parcelable
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
): Parcelable {

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(driverId)
        dest.writeString(permanentNumber)
        dest.writeString(code)
        dest.writeString(url)
        dest.writeString(givenName)
        dest.writeString(familyName)
        dest.writeString(dateOfBirth)
        dest.writeString(nationality)
    }

    companion object CREATOR : Parcelable.Creator<Driver> {
        override fun createFromParcel(parcel: Parcel): Driver {
            return Driver(parcel)
        }

        override fun newArray(size: Int): Array<Driver?> {
            return arrayOfNulls(size)
        }
    }

    private constructor(parcel: Parcel) : this(
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty()
    )
}
