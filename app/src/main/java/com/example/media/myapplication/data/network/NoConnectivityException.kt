package com.example.media.myapplication.data.network

import java.io.IOException

/**
 * Created by Harshal Chaudhari on 18/5/19.
 */
class NoConnectivityException: IOException() {

    override fun getLocalizedMessage(): String {
        return "No Connectivity Exception"
    }
}