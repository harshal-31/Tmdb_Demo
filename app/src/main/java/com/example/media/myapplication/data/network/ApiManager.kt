package com.example.media.myapplication.data.network

import android.content.Context
import com.example.media.myapplication.data.model.Credits
import com.example.media.myapplication.data.model.MovieDetail
import com.example.media.myapplication.util.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiManager(val context: Context) : ApiHelper {

    private val okHttpClient = OkHttpClient.Builder()
    private val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val gson = GsonBuilder().setPrettyPrinting().setLenient().serializeNulls().create()!!

    init {
        okHttpClient.connectTimeout(30_000, TimeUnit.MILLISECONDS)
        okHttpClient.readTimeout(30_000, TimeUnit.MILLISECONDS)
        okHttpClient.writeTimeout(30_000, TimeUnit.MILLISECONDS)
        okHttpClient.addInterceptor(httpLoggingInterceptor)
    }

    private fun getApiInterface() = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient.build())
        .build()
        .create(ApiInterface::class.java)


    override fun getTopRatedMovies(apiKey: String, language: String, page: Int) = getApiInterface().getTopRatedMovies(apiKey, language, page)

    override fun getMovieDetail(movieId: Int, apiKey: String): Call<MovieDetail> = getApiInterface().getMovieDetail(movieId, apiKey)

    override fun getMovieCredits(movieId: Int, apiKey: String): Call<Credits> = getApiInterface().getMovieCredits(movieId, apiKey)
}