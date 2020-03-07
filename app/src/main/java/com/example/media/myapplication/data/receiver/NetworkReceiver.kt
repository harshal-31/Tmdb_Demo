package com.example.media.myapplication.data.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.media.myapplication.data.network.NetworkUtils

class NetworkReceiver : BroadcastReceiver() {

    lateinit var networkCheck: NetworkCheck

    fun setListner(networkCheck: NetworkCheck) {
        this.networkCheck = networkCheck
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null && intent != null) {
            if (NetworkUtils.isNetworkConnected(context))
                networkCheck.isConnected(NetworkUtils.isNetworkConnected(context))
            else
                networkCheck.isConnected(false)
        }
    }


    interface NetworkCheck {
        fun isConnected(check: Boolean)
    }

}