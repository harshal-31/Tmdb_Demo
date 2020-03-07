package com.example.media.myapplication.util

import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.media.myapplication.R
import com.example.media.myapplication.base.EndlessScrollListener
import com.example.media.myapplication.ui.favorites.FavoriteViewModel
import com.example.media.myapplication.ui.movies.MoviesViewModel
import com.example.media.myapplication.ui.second_screen.SecondViewModel

/**
 * Created by Harshal Chaudhari on 5/3/20.
 */

object BindingUtil {

    @BindingAdapter("moviesViewModel")
    @JvmStatic
    fun RecyclerView.setMoviesModel(moviesViewModel: MoviesViewModel) {
        moviesViewModel.movieAdapter?.let {
            val manager = GridLayoutManager(context, 2)
            layoutManager = manager

            if (!it.hasObservers()) {
                it.setHasStableIds(true)
            }

            adapter = it

            addOnScrollListener(object : EndlessScrollListener(manager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                    if (moviesViewModel.isRefreshCall) {
                        resetState()
                        moviesViewModel.isRefreshCall = false
                    }
                    moviesViewModel.movieAdapter?.isLoad = getLoading
                    moviesViewModel.callMovies(page)
                }
            })
        }
    }

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun ImageView.loadImageWithPrefix(url: String?) {
        Glide.with(context)
            .load("${Constants.IMAGE_URL}${url ?: ""}")
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(
                RequestOptions.centerInsideTransform().diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(ContextCompat.getDrawable(context, R.drawable.ic_local_movies_black_24dp))
            )
            .into(this)
    }

    @BindingAdapter("favouriteViewModel")
    @JvmStatic
    fun RecyclerView.setFavouriteViewHolder(favoriteViewModel: FavoriteViewModel) {
        favoriteViewModel.favouriteAdapter?.let {
            val manager = GridLayoutManager(context, 2)
            layoutManager = manager

            if (!it.hasObservers()) {
                it.setHasStableIds(true)
            }
            adapter = it
        }
    }

    @BindingAdapter("secondViewModelCast")
    @JvmStatic
    fun RecyclerView.setMovieCastAdapter(secondViewModel: SecondViewModel) {
        secondViewModel.castAdapter?.let {
            val manager = LinearLayoutManager(context)
            manager.orientation = LinearLayoutManager.HORIZONTAL
            layoutManager = manager

            if (!it.hasObservers()) {
                it.setHasStableIds(true)
            }
            adapter = it
        }
    }
}