package com.example.media.myapplication.ui.favorites

import android.app.Application
import androidx.databinding.Bindable
import com.example.media.myapplication.base.BaseViewModel


class FavoriteViewModel(application: Application) : BaseViewModel(application) {

    @get: Bindable
    var favouriteAdapter: FavouriteAdapter? = null
        set(value) {
            field = value
            notifyChange()
        }
    val getFavouriteMovies = roomRepository.getHistoryVideoInfo()

}
