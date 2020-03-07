package com.example.vlcplayer.base

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.media.myapplication.base.BaseCellViewHolder
import com.example.media.myapplication.base.BaseCommonProgressViewHolder
import com.example.media.myapplication.base.BaseRecyclerItemClick


/**
 * Created by Harshal Chaudhari on 24/10/19.
 */
abstract class BaseRecyclerAdapter<T>() : RecyclerView.Adapter<BaseCellViewHolder<T>>() {

    lateinit var list: MutableList<T>
    lateinit var itemClick: BaseRecyclerItemClick<T>
    protected val RECYCLER_DATA = 0
    protected val LOADING = 1



    constructor(list: MutableList<T>, itemClick: BaseRecyclerItemClick<T>) : this() {
        this.list = list
        this.itemClick = itemClick
    }

    override fun onBindViewHolder(holder: BaseCellViewHolder<T>, position: Int) {
        if (getItemViewType(position) == RECYCLER_DATA) {
            val data = list[position]
            holder.bindData(data, itemClick)
        } else {
            val hold = (holder as BaseCommonProgressViewHolder)
            hold.bindData(isLoading())
        }
    }

    fun addLists(list1: List<T>) {
        list.addAll(list1)
        notifyDataSetChanged()
    }

    fun clearDataList () {
        list.clear()
        notifyDataSetChanged()
    }

    fun getDataList() = list

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int {
        return if (list.size - 1 == position && isLoading() && list.size > 10) LOADING else RECYCLER_DATA
    }

    override fun getItemCount(): Int = list.size

    //this is used to show loading bar on recycler view
    abstract fun isLoading(): Boolean
}

abstract class GenericViewHolder<V>(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
    var data: V? = null
    var itemClick: CommonRecyclerItemClick<V>? = null

    abstract fun bindData(data: V, position: Int, itemClick: CommonRecyclerItemClick<V>?)

}



interface CommonRecyclerItemClick<T> {
    fun onItemClick(data: T, position: Int, view: View) {}
}

