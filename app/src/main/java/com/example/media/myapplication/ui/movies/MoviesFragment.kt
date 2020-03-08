package com.example.media.myapplication.ui.movies

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.media.myapplication.ActivityViewModel
import com.example.media.myapplication.R
import com.example.media.myapplication.databinding.FragmentMoviesBinding
import com.example.media.myapplication.base.BaseFragment
import com.example.media.myapplication.base.BaseRecyclerItemClick
import com.example.media.myapplication.data.model.Movies
import com.example.media.myapplication.ui.second_screen.SecondActivity
import com.example.media.myapplication.util.Constants
import com.example.vlcplayer.data.room_database.MovieInfo

class MoviesFragment : BaseFragment<FragmentMoviesBinding, MoviesViewModel>(), BaseRecyclerItemClick<Movies>, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var parentModel: ActivityViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentModel = ViewModelProvider(baseActivity).get(ActivityViewModel::class.java)
        setUpUi()
        setUpListener()
        handleMutableEvent()
    }

    override fun setUpUi() {
        clearDataAndCallApi()
        binding.moviesSwipe.setOnRefreshListener(this)
        viewModel.movieAdapter = MoviesAdapter(emptyList<Movies>().toMutableList(), this)
    }

    override fun setUpListener() {

    }

    private fun handleMutableEvent() {
        viewModel.getTopRatedMovies.observe(viewLifecycleOwner, Observer {
            it?.let {
                viewModel.movieAdapter?.addLists(it.results.toMutableList())
            }
            viewModel.checkMovieIsFavouriteOrNot()
            viewModel.movieAdapter?.isLoad = false
            binding.moviesSwipe.isRefreshing = false
            viewModel.goneProgress.set(true)
        })

        parentModel.isFromMovies.observe(viewLifecycleOwner, Observer {
            it?.let {
                viewModel.makeNotifyDataAndInsertAndDeleteData(it.second, it.first)
                parentModel.isFromMovies.value = null
            }
        })

    }

    override fun onRefresh() {
        clearDataAndCallApi()
    }

    override fun onFavoriteClick(data: Movies, position: Int, view: ImageView) {
        viewModel.insertOrDeleteMovieInfo(data)
    }

    override fun onItemClick(data: Movies, position: Int) {
        val intent = Intent(baseActivity, SecondActivity::class.java)
        val likeOrNot = if (data.isFavourate) 1 else 0
        val movieInfo = MovieInfo(data.id ?: 0, data.posterPath ?: "", data.backdropPath ?: "", data.title ?: "", likeOrNot, data.totalPercent ?: 0)
        intent.putExtra(Constants.MOVIES_INFO, movieInfo)
        intent.putExtra(Constants.CURRENT_INDEX, position)
        startActivityForResult(intent, 13)
    }

    override fun onWithOrWithoutErrorBody(message: String) {
        super.onWithOrWithoutErrorBody(message)
        binding.moviesSwipe.isRefreshing = false
    }

    override fun onNetworkChanged(isChanged: Boolean, message: String) {
        super.onNetworkChanged(isChanged, message)
        binding.moviesSwipe.isRefreshing = false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 13 && resultCode == RESULT_OK) {
            val index = data?.getIntExtra(Constants.CURRENT_INDEX, 0) ?: 0
            val isFavorite = data?.getBooleanExtra(Constants.CHECK_FAVOURITE, false) ?: false
            viewModel.makeNotifyDataAndInsertAndDeleteData(isFavorite, index)
        }
    }

    override fun isShare(): Boolean = false
    override fun getModel(): Class<MoviesViewModel> = MoviesViewModel::class.java
    override fun getLayout(): Int = R.layout.fragment_movies
}
