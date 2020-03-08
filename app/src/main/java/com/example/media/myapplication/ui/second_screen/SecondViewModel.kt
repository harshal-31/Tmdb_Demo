package com.example.media.myapplication.ui.second_screen

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.viewModelScope
import com.example.media.myapplication.base.BaseViewModel
import com.example.media.myapplication.data.model.MovieDetail
import com.example.media.myapplication.util.Constants
import com.example.media.myapplication.util.getCurrencyValue
import com.example.media.myapplication.util.getHourAndMin
import com.example.vlcplayer.data.room_database.MovieInfo
import kotlinx.coroutines.launch

/**
 * Created by Harshal Chaudhari on 7/3/20.
 */
class SecondViewModel(application: Application) : BaseViewModel(application) {

    var isFavorite = false
    val movieTitle = ObservableField("")
    val genre = ObservableField("")
    val progress = ObservableInt(0)
    val overview =  ObservableField("")
    val runtime = ObservableField("")
    val budget = ObservableField("")
    val revenue = ObservableField("")
    val originalTitle = ObservableField("")
    val status = ObservableField("")
    val released = ObservableField("")
    val originalLanguage = ObservableField("")
    val progressString = ObservableField("")
    var isDisplay = ObservableBoolean(false)


    @get: Bindable
    var movieInfo: MovieInfo? = null
        set(value) {
            field = value
            notifyChange()
        }

    var movieInfoAdapter: MovieInfoAdapter = MovieInfoAdapter()

    var castAdapter: CastAdapter? = null
        set(value) {
            field = value
            notifyChange()
        }



    fun callMovieDetailAndCreditApi() {
        mainRepository.getMovieDetail(movieInfo?.movieId ?: 0, Constants.API_KEY)
        mainRepository.getMovieCredits(movieInfo?.movieId ?: 0, Constants.API_KEY)
    }

    fun fillAllDetailData(movies: MovieDetail) {
        headerTitle.set(movies.title ?: "")
        overview.set(movies.overview)
        movieTitle.set("${movies.title} (${if(!movies.releaseDate.isNullOrEmpty()) movies.releaseDate.take(4) else ""})")
        genre.set(movies.genres.joinToString{ it.name ?: "" })
        progress.set(movieInfo?.totalVote ?: 0)
        progressString.set(movieInfo?.totalVote.toString())
        runtime.set(movies.runtime?.getHourAndMin() ?: "")
        budget.set(movies.budget?.getCurrencyValue ?: "")
        revenue.set(movies.revenue?.getCurrencyValue ?: "")
        originalTitle.set(movies.originalTitle)
        status.set(movies.status)
        released.set(movies.releaseDate)
        originalLanguage.set(movies.originalLanguage)
    }



    fun insertOrDeleteMovieInfo() {
        movieInfo?.let {
            it.likeOrNot = if (isFavorite) 1 else 0
        }
        viewModelScope.launch {
            if (isFavorite) {
                roomRepository.insertMoviewInfo(movieInfo!!)
            } else {
                roomRepository.deleteVideoInfo(movieInfo!!)
            }
        }
    }
    val getMovieDetail = mainRepository.movieDetail
    val getMovieCredits = mainRepository.movieCredits
}