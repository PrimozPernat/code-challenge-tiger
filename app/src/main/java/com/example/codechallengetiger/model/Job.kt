package com.example.codechallengetiger.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.codechallengetiger.util.formatDate
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(primaryKeys = ["customerName", "date", "orderDuration"])
data class Job(
    @Json(name = "customer_name") val customerName: String,
    val distance: String,
    @Json(name = "job_date") val date: String,
    val extras: String,
    @Json(name = "order_duration") val orderDuration: Double,
    @Json(name = "order_time") val orderTime: String,
    @Json(name = "payment_method") val paymentMethod: String,
    val price: Double,
    val recurrency: Int,
    @Json(name = "job_city") val city: String,
    @Json(name = "job_postalcode") val postalCode: Int,
    @Json(name = "job_street") val address: String,
    val status: String
) : Parcelable {

    fun getLocation(): String {
        return "$address, $city, $postalCode"
    }

    fun getDateAndTime(): String {
        return "${date.formatDate()} at ${orderTime}"
    }
}