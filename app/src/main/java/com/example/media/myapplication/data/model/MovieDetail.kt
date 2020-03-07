package com.example.media.myapplication.data.model
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize

import com.google.gson.annotations.SerializedName


/**
 * Created by Harshal Chaudhari on 7/3/20.
 */

@Parcelize
data class MovieDetail(
    @SerializedName("adult")
    val adult: Boolean? = false, // false
    @SerializedName("backdrop_path")
    val backdropPath: String? = "", // /fCayJrkfRaCRCTh8GqN30f8oyQF.jpg
    @SerializedName("budget")
    val budget: Int? = 0, // 63000000
    @SerializedName("genres")
    val genres: List<Genre> = listOf(),
    @SerializedName("homepage")
    val homepage: String? = "",
    @SerializedName("id")
    val id: Int? = 0, // 550
    @SerializedName("imdb_id")
    val imdbId: String? = "", // tt0137523
    @SerializedName("original_language")
    val originalLanguage: String? = "", // en
    @SerializedName("original_title")
    val originalTitle: String? = "", // Fight Club
    @SerializedName("overview")
    val overview: String? = "", // A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground "fight clubs" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.
    @SerializedName("popularity")
    val popularity: Double? = 0.0, // 0.5
    @SerializedName("poster_path")
    val posterPath: String? = "", // null
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany?>? = listOf(),
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry?>? = listOf(),
    @SerializedName("release_date")
    val releaseDate: String? = "", // 1999-10-12
    @SerializedName("revenue")
    val revenue: Int? = 0, // 100853753
    @SerializedName("runtime")
    val runtime: Int? = 0, // 139
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage?>? = listOf(),
    @SerializedName("status")
    val status: String? = "", // Released
    @SerializedName("tagline")
    val tagline: String? = "", // How much can you know about yourself if you've never been in a fight?
    @SerializedName("title")
    val title: String? = "", // Fight Club
    @SerializedName("video")
    val video: Boolean? = false, // false
    @SerializedName("vote_average")
    val voteAverage: Double? = 0.0, // 7.8
    @SerializedName("vote_count")
    val voteCount: Int? = 0 // 3439
) : Parcelable

@Parcelize
data class Genre(
    @SerializedName("id")
    val id: Int? = 0, // 18
    @SerializedName("name")
    val name: String? = "" // Drama
) : Parcelable

@Parcelize
data class ProductionCompany(
    @SerializedName("id")
    val id: Int? = 0, // 25
    @SerializedName("logo_path")
    val logoPath: String? = "", // /qZCc1lty5FzX30aOCVRBLzaVmcp.png
    @SerializedName("name")
    val name: String? = "", // 20th Century Fox
    @SerializedName("origin_country")
    val originCountry: String? = "" // US
) : Parcelable

@Parcelize
data class ProductionCountry(
    @SerializedName("iso_3166_1")
    val iso31661: String? = "", // US
    @SerializedName("name")
    val name: String? = "" // United States of America
) : Parcelable

@Parcelize
data class SpokenLanguage(
    @SerializedName("iso_639_1")
    val iso6391: String? = "", // en
    @SerializedName("name")
    val name: String? = "" // English
) : Parcelable