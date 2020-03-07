package com.example.media.myapplication.data.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor(private val mContext : Context): Interceptor  {

    override fun intercept(chain: Interceptor.Chain): Response  {

        try {
            val builder = chain.request().newBuilder()


            return chain.proceed(builder.build())

        } catch (e: Exception) {
            throw IOException("No Connectivity Exception")
        }

    }
}