package com.example.media.myapplication.ui.favorites

import android.app.Application
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.example.media.myapplication.base.BaseViewModel
import com.example.vlcplayer.data.room_database.MovieInfo
import kotlinx.coroutines.launch


class FavoriteViewModel(application: Application) : BaseViewModel(application) {

    var currentSelectedItem: Int = 0

    @get: Bindable
    var favouriteAdapter: FavouriteAdapter? = null
        set(value) {
            field = value
            notifyChange()
        }

    fun insertOrDeleteMovieInfo(data: MovieInfo) {
        viewModelScope.launch {
            if (data.likeOrNot == 1) {
                roomRepository.insertMoviewInfo(data)
            } else {
                roomRepository.deleteVideoInfo(data)
            }
        }
    }


    val getFavouriteMovies = roomRepository.getHistoryVideoInfo()

}
