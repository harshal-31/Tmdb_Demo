package com.example.media.myapplication.base

/**
 * Created by Harshal Chaudhari on 27/7/19.
 */

//Base Interface for all kind of error handling on whole application
interface BaseErrorCallback {
    fun onNetworkChanged(isChanged: Boolean, message: String) {}
    fun onError401(message: String) {}
    fun onWithOrWithoutErrorBody(message: String) {}
}




