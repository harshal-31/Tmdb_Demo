package com.example.media.myapplication.data

import android.content.Context
import com.example.media.myapplication.data.model.Credits
import com.example.media.myapplication.data.model.MovieDetail
import com.example.media.myapplication.data.network.ApiHelper
import retrofit2.Call

class AppDataManager(val context: Context, private val apiHelper: ApiHelper) : DataManager {


    override fun getTopRatedMovies(apiKey: String, language: String, page: Int) = apiHelper.getTopRatedMovies(apiKey, language, page)

    override fun getMovieDetail(movieId: Int, apiKey: String): Call<MovieDetail> = apiHelper.getMovieDetail(movieId, apiKey)

    override fun getMovieCredits(movieId: Int, apiKey: String): Call<Credits> = apiHelper.getMovieCredits(movieId, apiKey)
}
