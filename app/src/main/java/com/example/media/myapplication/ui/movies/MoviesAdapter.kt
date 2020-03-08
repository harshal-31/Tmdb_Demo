package com.example.media.myapplication.ui.movies

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
import com.example.media.myapplication.base.BaseCommonProgressViewHolder
import com.example.media.myapplication.base.BaseRecyclerItemClick
import com.example.media.myapplication.data.model.Movies
import com.example.media.myapplication.databinding.CommonAdapterProgressLayoutBinding
import com.example.media.myapplication.databinding.MoviesViewHolderBinding
import com.example.media.myapplication.util.calculatePercent
import com.example.vlcplayer.base.BaseRecyclerAdapter

/**
 * Created by Harshal Chaudhari on 6/3/20.
 */
class MoviesAdapter(list: MutableList<Movies>, itemClick: BaseRecyclerItemClick<Movies>) : BaseRecyclerAdapter<Movies>(list, itemClick) {

    var isLoad: Boolean = false

    override fun isLoading(): Boolean = isLoad

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseCellViewHolder<Movies> {
        return if (viewType == RECYCLER_DATA) {
            val binding: MoviesViewHolderBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.movies_view_holder, parent, false)
            MoviesViewHolder(binding)
        } else {
            val binding: CommonAdapterProgressLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.common_adapter_progress_layout, parent, false)
            BaseCommonProgressViewHolder(binding)
        }
    }

    inner class MoviesViewHolder(val binding: MoviesViewHolderBinding) : BaseCellViewHolder<Movies>(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
            binding.ivFavorite.setOnClickListener(this)
        }

        override fun bindData(data: Movies, click: BaseRecyclerItemClick<Movies>) {
            data.totalPercent = data.voteAverage?.calculatePercent ?: 0
            data.isFavourate = list[adapterPosition].isFavourate
            binding.ivFavorite.setImageDrawable(ContextCompat.getDrawable(binding.root.context, if (data.isFavourate) R.drawable.ic_favorite_black_fill_24dp else R.drawable.ic_favorite_border_black_24dp))
            binding.movies = data
            this.data = data
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onClick(p0: View?) {
            p0?.let {
                when (it.id) {
                    binding.root.id       -> itemClick.onItemClick(data!!, adapterPosition)
                    binding.ivFavorite.id -> {
                        makeHeartItemClick(data!!)
                        itemClick.onFavoriteClick(data!!, adapterPosition, binding.ivFavorite)
                    }
                }
            }
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun makeHeartItemClick(data: Movies) {
            if (!data.isFavourate) {
                data.isFavourate = true
                binding.ivFavorite.setImageResource(R.drawable.like)
                (binding.ivFavorite.drawable as AnimatedVectorDrawable).start()
                notifyItemChanged(adapterPosition)
            } else {
                data.isFavourate = false
                binding.ivFavorite.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.ic_favorite_border_black_24dp))
                notifyItemChanged(adapterPosition)
            }
        }
    }


}