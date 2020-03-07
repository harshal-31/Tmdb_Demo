package com.example.media.myapplication.ui.favorites

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.media.myapplication.base.BaseFragment
import com.example.media.myapplication.R
import com.example.media.myapplication.base.BaseRecyclerItemClick
import com.example.media.myapplication.databinding.FragmentFavoritesBinding
import com.example.vlcplayer.data.room_database.MovieInfo
import kotlinx.coroutines.flow.collect

class FavoritesFragment : BaseFragment<FragmentFavoritesBinding, FavoriteViewModel>(), BaseRecyclerItemClick<MovieInfo> {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        setUpListener()
    }


    override fun setUpUi() {
        viewModel.favouriteAdapter = FavouriteAdapter(emptyList<MovieInfo>().toMutableList(), this)
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.getFavouriteMovies.collect {
                viewModel.favouriteAdapter?.clearDataList()
                viewModel.favouriteAdapter?.addLists(it)
            }
        }

    }

    override fun setUpListener() {


    }


    override fun isShare(): Boolean = false
    override fun getModel(): Class<FavoriteViewModel> = FavoriteViewModel::class.java
    override fun getLayout(): Int = R.layout.fragment_favorites
}
