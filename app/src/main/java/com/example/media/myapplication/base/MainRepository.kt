package com.example.media.myapplication.base

import android.app.Application
import com.example.media.myapplication.data.AppDataManager
import com.example.media.myapplication.data.DataManager
import com.example.media.myapplication.data.network.ApiManager
import com.example.media.myapplication.util.RetroResponse.*
import com.example.media.myapplication.util.retroCall

//class MainRepository { used for all of api call from back end }
class MainRepository(application: Application) : BaseRepository(), ApiCallFunction {

    private val dataManager: DataManager
    private lateinit var errorCallback: BaseErrorCallback

    init {
        dataManager = AppDataManager(application.applicationContext, ApiManager(application.applicationContext))
    }

    fun setBaseErrorCallback(callback: BaseErrorCallback) {
        this.errorCallback = callback
    }



    override fun getTopRatedMovies(apiKey: String, language: String, page: Int) {
        retroCall(dataManager.getTopRatedMovies(apiKey, language, page)) {
            when (it) {
                is Success             -> moviesData.value = it.data
                is Failure             -> moviesData.value = null
                is NullData            -> moviesData.value = null
                is OnNetworkChange     -> errorCallback.onNetworkChanged(it.isChanged, it.message)
                is OnResponseCodeError -> errorCallback.onWithOrWithoutErrorBody(it.message)
            }
        }
    }

    override fun getMovieDetail(movieId: Int, apiKey: String) {
        retroCall(dataManager.getMovieDetail(movieId, apiKey)) {
            when (it) {
                is Success             -> movieDetail.value = it.data
                is Failure             -> movieDetail.value = null
                is NullData            -> movieDetail.value = null
                is OnNetworkChange     -> errorCallback.onNetworkChanged(it.isChanged, it.message)
                is OnResponseCodeError -> errorCallback.onWithOrWithoutErrorBody(it.message)
            }
        }
    }

    override fun getMovieCredits(movieId: Int, apiKey: String) {
        retroCall(dataManager.getMovieCredits(movieId, apiKey)) {
            when (it) {
                is Success             -> movieCredits.value = it.data
                is Failure             -> movieCredits.value = null
                is NullData            -> movieCredits.value = null
                is OnNetworkChange     -> errorCallback.onNetworkChanged(it.isChanged, it.message)
                is OnResponseCodeError -> errorCallback.onWithOrWithoutErrorBody(it.message)
            }
        }
    }
}


