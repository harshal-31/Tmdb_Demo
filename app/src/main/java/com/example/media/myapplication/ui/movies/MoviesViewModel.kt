package com.example.media.myapplication.ui.movies

import android.app.Application
import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.media.myapplication.base.BaseViewModel
import com.example.media.myapplication.data.model.Movies
import com.example.media.myapplication.util.Constants
import com.example.vlcplayer.data.room_database.MovieInfo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class MoviesViewModel(application: Application) : BaseViewModel(application) {

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
                    for (movieInfos in movieInfo) {
                        Log.d("Index Pos","indexPos  $index")
                        if (movieInfos.movieId== movies.id ?: 0) {
                            if ((movieAdapter?.getDataList() as List<Movies>).isNotEmpty()) {
                                (movieAdapter?.getDataList() as List<Movies>)[index].isFavourate = true
                                movieAdapter?.notifyItemChanged(index)
                            }
                        }
                    }
                }
            }
        }
    }

    fun makeNotifyDataAndInsertAndDeleteData(favorite: Boolean, index: Int) {
        if (index != RecyclerView.NO_POSITION) {
            (movieAdapter?.getDataList() as List<Movies>)[index].isFavourate = favorite
            insertOrDeleteMovieInfo((movieAdapter?.getDataList() as List<Movies>)[index])
            movieAdapter?.notifyItemChanged(index)
        }
    }


    val getTopRatedMovies = mainRepository.moviesData

    val getFavouriteMovies = roomRepository.getHistoryVideoInfo()

}
