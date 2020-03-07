package com.example.media.myapplication.base

import androidx.lifecycle.MutableLiveData
import com.example.media.myapplication.data.model.Configuration
import com.example.media.myapplication.data.model.TmdbMovies


/**
 * Created by Harshal Chaudhari on 13/4/19.
 */

//class CommonRepository { used for all mutable live data object container }
abstract class BaseRepository {
    val configurationData = MutableLiveData<Configuration>()
    val moviesData = MutableLiveData<TmdbMovies>()
}
