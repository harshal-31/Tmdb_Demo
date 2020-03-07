package com.example.media.myapplication.base

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.media.myapplication.databinding.CommonAdapterProgressLayoutBinding


/**
 * Created by Harshal Chaudhari on 10/8/19.
 */


class BaseCommonProgressViewHolder<T>(val binding: CommonAdapterProgressLayoutBinding) : BaseCellViewHolder<T>(binding.root) {

    override fun bindData(data: T, click: BaseRecyclerItemClick<T>) {

    }

    fun bindData(isLoad: Boolean) {
        binding.isLoading = isLoad
    }

}

abstract class BaseCellViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var click: BaseRecyclerItemClick<T>? = null
    var data: T? = null

    abstract fun bindData(data: T, click: BaseRecyclerItemClick<T>)
}

interface BaseRecyclerItemClick<T> {
    fun onItemClick(data: T, position: Int) {}
    fun onFavoriteClick(data: T, position: Int, view: ImageView) {}
}

interface CommonRecyclerClick {
    fun onItemClick(data: Any, itemView: View, position: Int)
}