package com.example.media.myapplication.ui.favorites

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
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
            binding.ivFavorite.setOnClickListener(this)
        }

        override fun bindData(data: MovieInfo, click: BaseRecyclerItemClick<MovieInfo>) {
            this.data = data
            binding.movies = data
            binding.ivFavorite.setImageDrawable(ContextCompat.getDrawable(binding.root.context, if (data.likeOrNot == 1) R.drawable.ic_favorite_black_fill_24dp else R.drawable.ic_favorite_border_black_24dp))
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onClick(p0: View?) {
            if (binding.root.id == p0?.id) {
                itemClick.onItemClick(data!!, adapterPosition)
            } else if (binding.ivFavorite.id == p0?.id) {
                makeHeartItemClick()
                itemClick.onFavoriteClick(data!!, adapterPosition, binding.ivFavorite)
            }
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun makeHeartItemClick() {
            if (list[adapterPosition].likeOrNot != 0) {
                list[adapterPosition].likeOrNot = 0
                binding.ivFavorite.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.ic_favorite_border_black_24dp))
                notifyItemChanged(adapterPosition)
            } else {
                list[adapterPosition].likeOrNot = 1
                binding.ivFavorite.setImageResource(R.drawable.like)
                (binding.ivFavorite.drawable as AnimatedVectorDrawable).start()
                notifyItemChanged(adapterPosition)
            }
        }
    }
}