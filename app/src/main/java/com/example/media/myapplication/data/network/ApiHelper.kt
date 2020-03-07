package com.example.media.myapplication.data.network

import com.example.media.myapplication.data.model.Configuration
import com.example.media.myapplication.data.model.TmdbMovies
import retrofit2.Call


interface ApiHelper {


    fun getTopRatedMovies(apiKey: String, language: String, page: Int): Call<TmdbMovies>
}
