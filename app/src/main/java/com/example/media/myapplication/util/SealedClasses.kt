package com.example.media.myapplication.util

import java.util.*

/**
 * Created by Harshal Chaudhari on 5/3/20.
 */



sealed class RetroResponse<out T> {
    data class Success<T>(val data: T, val time: Date? = null) : RetroResponse<T>()
    data class Failure<T>(val throwable: Throwable) : RetroResponse<T>()
    class NullData<T> : RetroResponse<T>()
    data class OnNetworkChange<T>(val isChanged: Boolean = false, val message: String = "") : RetroResponse<T>()
    data class OnResponseCodeError<T>(val message: String) : RetroResponse<T>()
}
