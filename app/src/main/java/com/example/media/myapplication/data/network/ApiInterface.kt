package com.example.media.myapplication.data.network

import com.example.media.myapplication.data.model.Configuration
import com.example.media.myapplication.data.model.TmdbMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String, @Query("language") language: String, @Query("page") page: Int): Call<TmdbMovies>
}