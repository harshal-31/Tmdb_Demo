package com.example.media.myapplication.data.network

import com.example.media.myapplication.data.model.Configuration
import com.example.media.myapplication.data.model.Credits
import com.example.media.myapplication.data.model.MovieDetail
import com.example.media.myapplication.data.model.TmdbMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiHelper {


    fun getTopRatedMovies(apiKey: String, language: String, page: Int): Call<TmdbMovies>

    fun getMovieDetail(movieId: Int, apiKey: String): Call<MovieDetail>

    fun getMovieCredits(movieId: Int, apiKey: String): Call<Credits>

}
