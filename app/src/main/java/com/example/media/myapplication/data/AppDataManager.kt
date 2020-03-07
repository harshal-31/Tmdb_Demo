package com.example.media.myapplication.data

import android.content.Context
import com.example.media.myapplication.data.network.ApiHelper

class AppDataManager(val context: Context, private val apiHelper: ApiHelper) : DataManager {


    override fun getTopRatedMovies(apiKey: String, language: String, page: Int) = apiHelper.getTopRatedMovies(apiKey, language, page)

}
