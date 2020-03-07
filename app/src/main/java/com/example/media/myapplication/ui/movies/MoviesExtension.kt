package com.example.media.myapplication.ui.movies

/**
 * Created by Harshal Chaudhari on 6/3/20.
 */


 fun MoviesFragment.clearDataAndCallApi() {
    viewModel.isRefreshCall = true
    viewModel.movieAdapter?.clearDataList()
    viewModel.callMovies(1)
    binding.moviesSwipe.isRefreshing = true
    viewModel.goneProgress.set(false)
 }