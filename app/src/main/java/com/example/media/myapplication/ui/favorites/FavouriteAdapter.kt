package com.example.media.myapplication.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.media.myapplication.R
import com.example.media.myapplication.base.BaseCellViewHolder
import com.example.media.myapplication.base.BaseRecyclerItemClick
import com.example.media.myapplication.databinding.FavouriteViewHolderBinding
import com.example.vlcplayer.base.BaseRecyclerAdapter
import com.example.vlcplayer.data.room_database.MovieInfo

/**
 * Created by Harshal Chaudhari on 6/3/20.
 */

 class FavouriteAdapter(list: MutableList<MovieInfo>, itemClick: BaseRecyclerItemClick<MovieInfo>) : BaseRecyclerAdapter<MovieInfo>(list, itemClick) {

    override fun isLoading(): Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseCellViewHolder<MovieInfo> {
        val binding: FavouriteViewHolderBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.favourite_view_holder, parent, false)
        return FavouriteViewHolder(binding)
    }

    inner class FavouriteViewHolder(val binding: FavouriteViewHolderBinding) : BaseCellViewHolder<MovieInfo>(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }

        override fun bindData(data: MovieInfo, click: BaseRecyclerItemClick<MovieInfo>) {
            this.data = data
            binding.movies = data
            binding.movieProressCount.text = data.totalVote.toString()
            binding.ivFavorite.setImageDrawable(ContextCompat.getDrawable(binding.root.context, if (data.likeOrNot == 1) R.drawable.ic_favorite_black_fill_24dp else R.drawable.ic_favorite_border_black_24dp))
        }

        override fun onClick(p0: View?) {
            itemClick.onItemClick(data!!, adapterPosition)
        }
    }

}