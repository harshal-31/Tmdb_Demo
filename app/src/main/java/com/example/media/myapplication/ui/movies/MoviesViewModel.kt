package com.example.media.myapplication.ui.movies

import android.app.Application
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.example.media.myapplication.base.BaseViewModel
import com.example.media.myapplication.data.model.Movies
import com.example.media.myapplication.util.Constants
import com.example.vlcplayer.data.room_database.MovieInfo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class MoviesViewModel(application: Application) : BaseViewModel(application) {

    var currentSelectedItem: Int = 0

    @get: Bindable
    var movieAdapter: MoviesAdapter? = null
        set(value) {
            field = value
            notifyChange()
        }

    var isRefreshCall = false


    fun callMovies(pageNo: Int = 1) {
        mainRepository.getTopRatedMovies(Constants.API_KEY, Constants.Language, pageNo)
    }

    fun insertOrDeleteMovieInfo(data: Movies) {
        val likeOrNot = if (data.isFavourate) 1 else 0
        val movieInfo = MovieInfo(data.id ?: 0, data.posterPath ?: "",data.backdropPath ?: "", data.title ?: "", likeOrNot, data.totalPercent ?: 0)
        viewModelScope.launch {
            if (data.isFavourate) {
                roomRepository.insertMoviewInfo(movieInfo)
            } else {
                roomRepository.deleteVideoInfo(movieInfo)
            }
        }
    }

    fun checkMovieIsFavouriteOrNot() {
        movieAdapter?.getDataList()?.asSequence()?.forEachIndexed { index, movies ->
            viewModelScope.launch {
                getFavouriteMovies.collect { movieInfo: List<MovieInfo> ->
                    movieInfo.forEach {  localMovie ->
                        if (localMovie.movieId == movies.id ?: 0) {
                            (movieAdapter?.getDataList() as List<Movies>)[index].isFavourate = true
                            movieAdapter?.notifyItemChanged(index)
                        }
                    }
                }
            }
        }
    }

    fun makeNotifyDataAndInsertAndDeleteData(favorite: Boolean) {
        (movieAdapter?.getDataList() as List<Movies>)[currentSelectedItem].isFavourate = favorite
        insertOrDeleteMovieInfo((movieAdapter?.getDataList() as List<Movies>)[currentSelectedItem])
        movieAdapter?.notifyItemChanged(currentSelectedItem)
    }


    val getTopRatedMovies = mainRepository.moviesData

    val getFavouriteMovies = roomRepository.getHistoryVideoInfo()

}
