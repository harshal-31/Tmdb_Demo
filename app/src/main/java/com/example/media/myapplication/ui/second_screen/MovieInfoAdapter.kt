package com.example.media.myapplication.ui.second_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.media.myapplication.R
import com.example.media.myapplication.databinding.MoviesInfoViewHolderBinding

/**
 * Created by Harshal Chaudhari on 7/3/20.
 */

 class MovieInfoAdapter: RecyclerView.Adapter<MoviesInfoViewHolder>() {
    private var map = mapOf<String, String>()

    fun setData(map: Map<String, String>) {
        this.map = map
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesInfoViewHolder {
        val binding: MoviesInfoViewHolderBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.movies_info_view_holder, parent, false)
        return MoviesInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesInfoViewHolder, position: Int) {
        holder.bindData(map, position)
    }

    override fun getItemCount(): Int = map.size
}

 class MoviesInfoViewHolder(val binding: MoviesInfoViewHolderBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindData(map: Map<String, String>, position: Int) {
        binding.key = map.keys.toMutableList()[position]
        binding.value = map.values.toMutableList()[position]
    }
}