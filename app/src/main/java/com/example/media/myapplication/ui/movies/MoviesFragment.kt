package com.example.media.myapplication.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.media.myapplication.R
import com.example.media.myapplication.base.BaseFragment
import com.example.media.myapplication.base.BaseRecyclerItemClick
import com.example.media.myapplication.data.model.Movies
import com.example.media.myapplication.databinding.FragmentMoviesBinding
import com.example.media.myapplication.ui.second_screen.SecondActivity
import com.example.media.myapplication.util.Constants
import com.example.vlcplayer.data.room_database.MovieInfo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MoviesFragment : BaseFragment<FragmentMoviesBinding, MoviesViewModel>(), BaseRecyclerItemClick<Movies>, SwipeRefreshLayout.OnRefreshListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    }

    override fun onRefresh() {
        clearDataAndCallApi()
    }

    override fun onFavoriteClick(data: Movies, position: Int, view: ImageView) {
        viewModel.insertOrDeleteMovieInfo(data)
    }

    override fun onItemClick(data: Movies, position: Int) {
        val intent = Intent(baseActivity, SecondActivity::class.java)
        intent.putExtra(Constants.MOVIES, data)
        startActivity(intent)
    }

    override fun onWithOrWithoutErrorBody(message: String) {
        super.onWithOrWithoutErrorBody(message)
        binding.moviesSwipe.isRefreshing = false
    }

    override fun onNetworkChanged(isChanged: Boolean, message: String) {
        super.onNetworkChanged(isChanged, message)
        binding.moviesSwipe.isRefreshing = false
    }

    override fun isShare(): Boolean = false
    override fun getModel(): Class<MoviesViewModel> = MoviesViewModel::class.java
    override fun getLayout(): Int = R.layout.fragment_movies
}
