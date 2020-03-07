package com.example.media.myapplication.ui.second_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.media.myapplication.R
import com.example.media.myapplication.base.BaseCellViewHolder
import com.example.media.myapplication.base.BaseRecyclerItemClick
import com.example.media.myapplication.data.model.Cast
import com.example.media.myapplication.databinding.CastViewHolderBinding
import com.example.vlcplayer.base.BaseRecyclerAdapter

/**
 * Created by Harshal Chaudhari on 7/3/20.
 */


class CastAdapter(list: MutableList<Cast>, itemClick: BaseRecyclerItemClick<Cast>) : BaseRecyclerAdapter<Cast>(list, itemClick) {
    override fun isLoading(): Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseCellViewHolder<Cast> {
        val binding: CastViewHolderBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.cast_view_holder, parent, false)
        return CastViewHolder(binding)
    }

    inner class CastViewHolder(val binding: CastViewHolderBinding) : BaseCellViewHolder<Cast>(binding.root) {
        override fun bindData(data: Cast, click: BaseRecyclerItemClick<Cast>) {
            binding.cast = data
        }
    }
}