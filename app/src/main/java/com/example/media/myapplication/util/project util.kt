package com.example.media.myapplication.util

import android.annotation.SuppressLint
import android.icu.text.NumberFormat
import android.os.Build
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.annotation.RequiresApi
import com.example.media.myapplication.base.BaseErrorCallback
import com.example.media.myapplication.data.model.BaseErrorData
import com.example.media.myapplication.data.model.ErrorMesssage
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Harshal Chaudhari on 5/3/20.
 */


internal inline fun <T> retroCall(call: Call<T>, errorCallback: BaseErrorCallback? = null, crossinline block: (RetroResponse<T>) -> Unit) {
    call.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            val headers = response.headers()
            if (response.body() != null && response.isSuccessful && response.code() == 200) {
                val date = headers.getDate("Date")
                block(RetroResponse.Success(response.body()!!, date))
            } else {
                when {
                    response.body() != null -> {
                        block(RetroResponse.OnResponseCodeError((response.body()!! as BaseErrorData).statusMessage ?: ""))
                    }

                    response.body() == null -> {

                        try {
                            var errorMesssage: ErrorMesssage? = null
                            if (response.errorBody() != null) {
                                val gson = Gson()
                                errorMesssage = gson.fromJson<ErrorMesssage>(response.errorBody()!!.charStream(), ErrorMesssage::class.java)
                            }
                            block(RetroResponse.OnResponseCodeError(errorMesssage?.message ?: "Something Went Wrong"))

                        } catch (e: Exception) {
                            block(RetroResponse.OnResponseCodeError("Something Went Wrong"))
                        }
                    }
                    else                    -> block(RetroResponse.NullData())
                }
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            when (t) {
                is IOException -> block(RetroResponse.OnNetworkChange(true, "Please check your Internet Connection "))
                else           -> block(RetroResponse.Failure(t))
            }
        }
    })
}


internal fun View.shortSnack(message: Any) {
    if (this.isShown) {
        Snackbar.make(this, message as String, Snackbar.LENGTH_SHORT).show()
    }
}

internal fun View.longSnack(message: Any) {
    if (this.isShown) {
        Snackbar.make(this, message as String, Snackbar.LENGTH_LONG).show()
    }
}

val Double.calculatePercent: Int
    get() = ((this * 100) / 10).toInt()


fun View.animateView() {
    this.apply {
        val animation1 = AlphaAnimation(0.2f, 1.0f)
        animation1.duration = 300
        alpha = 1f
        startAnimation(animation1)
    }
}





fun Int.getHourAndMin(): String {
    val data = this ?: 0
    val hour = data / 60
    val min = data % 60

    return "$hour Hour $min Min"
}

val Int.getCurrencyValue: String
    @RequiresApi(Build.VERSION_CODES.N)
    get() = NumberFormat.getCurrencyInstance(Locale("en","US")).format(this)





