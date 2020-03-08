package com.example.media.myapplication.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.media.myapplication.MainActivity
import com.example.media.myapplication.R
import com.example.media.myapplication.util.changeStatusBarColor
import kotlinx.coroutines.*

/**
 * Created by Harshal Chaudhari on 8/3/20.
 */


class SplashScreenActivity : AppCompatActivity() {

    private val job: Job
        get() = Job()

    private val ioScope = CoroutineScope(Dispatchers.IO + job)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeStatusBarColor(ContextCompat.getColor(this, R.color.original_black))
        setContentView(R.layout.activity_splash_screen)
        ioScope.launch {
            delay(3000)
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            finish()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
