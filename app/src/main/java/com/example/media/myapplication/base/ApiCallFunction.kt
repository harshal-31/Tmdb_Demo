package com.example.media.myapplication.base

/**
 * Created by Harshal Chaudhari on 6/3/20.
 */
interface ApiCallFunction {

    fun getTopRatedMovies(apiKey: String, language: String, page: Int)
}