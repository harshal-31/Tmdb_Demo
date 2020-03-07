package com.example.media.myapplication.data.model
import android.annotation.SuppressLint
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize

import com.google.gson.annotations.SerializedName


/**
 * Created by Harshal Chaudhari on 7/3/20.
 */


 @SuppressLint("ParcelCreator")
@Parcelize
data class Credits(
    @SerializedName("cast")
    val cast: List<Cast> = listOf(),
    @SerializedName("crew")
    val crew: List<Crew> = listOf(),
    @SerializedName("id")
    val id: Int? = 0 // 550
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Cast(
    @SerializedName("cast_id")
    val castId: Int? = 0, // 108
    @SerializedName("character")
    val character: String? = "", // Man at the Club
    @SerializedName("credit_id")
    val creditId: String? = "", // 58865460c3a3684480003a41
    @SerializedName("gender")
    val gender: Int? = 0, // 0
    @SerializedName("id")
    val id: Int? = 0, // 1744151
    @SerializedName("name")
    val name: String? = "", // Gökhan Öncel
    @SerializedName("order")
    val order: Int? = 0, // 76
    @SerializedName("profile_path")
    val profilePath: String? = "" // /bqbjYLTFjE4aoYOntybwP2TmzEZ.jpg
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Crew(
    @SerializedName("credit_id")
    val creditId: String? = "", // 57fe1e549251410699007177
    @SerializedName("department")
    val department: String? = "", // Costume & Make-Up
    @SerializedName("gender")
    val gender: Int? = 0, // 1
    @SerializedName("id")
    val id: Int? = 0, // 1693424
    @SerializedName("job")
    val job: String? = "", // Assistant Costume Designer
    @SerializedName("name")
    val name: String? = "", // Mirela Rupic
    @SerializedName("profile_path")
    val profilePath: String? = "" // /5z0I2eRwBrJjSv27ig4VnU0lmCZ.jpg
) : Parcelable