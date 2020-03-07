package com.example.media.myapplication.ui.second_screen

import android.app.Application
import androidx.databinding.Bindable
import com.example.media.myapplication.base.BaseViewModel
import com.example.media.myapplication.data.model.Movies
import com.example.vlcplayer.data.room_database.MovieInfo

/**
 * Created by Harshal Chaudhari on 7/3/20.
 */
class SecondViewModel(application: Application) : BaseViewModel(application) {

    @get: Bindable
    var movieInfo: MovieInfo? = null
    set(value) {
        field = value
        notifyChange()
    }

    @get: Bindable
    var moviesData: Movies? = null
        set(value) {
            field = value
            notifyChange()
        }
}