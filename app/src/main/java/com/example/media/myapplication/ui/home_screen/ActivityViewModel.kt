package com.example.media.myapplication

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.media.myapplication.base.BaseViewModel

/**
 * Created by Harshal Chaudhari on 5/3/20.
 */
 class ActivityViewModel(application: Application) : BaseViewModel(application) {
    val isFromMovies = MutableLiveData<Pair<Int, Boolean>>(null)

}