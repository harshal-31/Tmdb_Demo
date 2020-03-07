package com.example.media.myapplication.data.model
import android.annotation.SuppressLint
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize

import com.google.gson.annotations.SerializedName


/**
 * Created by Harshal Chaudhari on 5/3/20.
 */
 
@Parcelize
data class TmdbMovies(
    @SerializedName("page")
    val page: Int? = 0, // 1
    @SerializedName("results")
    val results: List<Movies> = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int? = 0, // 261
    @SerializedName("total_results")
    val totalResults: Int? = 0 // 5206
) : Parcelable, BaseErrorData()

@Parcelize
data class Movies(
    @SerializedName("adult")
    val adult: Boolean? = false, // false
    @SerializedName("backdrop_path")
    val backdropPath: String? = "", // /61vLiK96sbXeHpQiMxI4CuqBA3z.jpg
    @SerializedName("genre_ids")
    val genreIds: List<Int?>? = listOf(),
    @SerializedName("id")
    val id: Int? = 0, // 346
    @SerializedName("original_language")
    val originalLanguage: String? = "", // ja
    @SerializedName("original_title")
    val originalTitle: String? = "", // 七人の侍
    @SerializedName("overview")
    val overview: String? = "", // A veteran samurai, who has fallen on hard times, answers a village's request for protection from bandits. He gathers 6 other samurai to help him, and they teach the townspeople how to defend themselves, and they supply the samurai with three small meals a day. The film culminates in a giant battle when 40 bandits attack the village.
    @SerializedName("popularity")
    val popularity: Double? = 0.0, // 2.93856
    @SerializedName("poster_path")
    val posterPath: String? = "", // /5hqbJSmtAimbaP3XcYshCixuUtk.jpg
    @SerializedName("release_date")
    val releaseDate: String? = "", // 1954-04-26
    @SerializedName("title")
    val title: String? = "", // Seven Samurai
    @SerializedName("video")
    val video: Boolean? = false, // false
    @SerializedName("vote_average")
    val voteAverage: Double? = 0.0, // 8.02
    @SerializedName("vote_count")
    val voteCount: Int? = 0, // 436
    var isFavourate: Boolean =  false,
    var totalPercent: Int? = 0
) : Parcelable


@Parcelize
open class BaseErrorData(
    @SerializedName("status_code")
    val statusCode: Int? = 0, // 7
    @SerializedName("status_message")
    val statusMessage: String? = "", // Invalid API key: You must be granted a valid key.
    @SerializedName("success")
    val success: Boolean? = false // false
) : Parcelable


@SuppressLint("ParcelCreator")
@Parcelize
data class Configuration(
    @SerializedName("change_keys")
    val changeKeys: List<String> = listOf(),
    @SerializedName("images")
    val images: Images? = Images()
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Images(
    @SerializedName("backdrop_sizes")
    val backdropSizes: List<String> = listOf(),
    @SerializedName("base_url")
    val baseUrl: String? = "", // http://image.tmdb.org/t/p/
    @SerializedName("logo_sizes")
    val logoSizes: List<String> = listOf(),
    @SerializedName("poster_sizes")
    val posterSizes: List<String> = listOf(),
    @SerializedName("profile_sizes")
    val profileSizes: List<String> = listOf(),
    @SerializedName("secure_base_url")
    val secureBaseUrl: String? = "", // https://image.tmdb.org/t/p/
    @SerializedName("still_sizes")
    val stillSizes: List<String> = listOf()
) : Parcelable
