package com.example.media.myapplication.ui.favorites

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.media.myapplication.ActivityViewModel
import com.example.media.myapplication.R
import com.example.media.myapplication.base.BaseFragment
import com.example.media.myapplication.base.BaseRecyclerItemClick
import com.example.media.myapplication.databinding.FragmentFavoritesBinding
import com.example.media.myapplication.ui.second_screen.SecondActivity
import com.example.media.myapplication.util.Constants
import com.example.media.myapplication.util.shortSnack
import com.example.vlcplayer.data.room_database.MovieInfo
import kotlinx.coroutines.flow.collect

class FavoritesFragment : BaseFragment<FragmentFavoritesBinding, FavoriteViewModel>(), BaseRecyclerItemClick<MovieInfo> {
    private lateinit var parentModel: ActivityViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentModel = ViewModelProvider(baseActivity).get(ActivityViewModel::class.java)
        setUpUi()
        setUpListener()
    }


    override fun setUpUi() {
        viewModel.favouriteAdapter = FavouriteAdapter(emptyList<MovieInfo>().toMutableList(), this)
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.getFavouriteMovies.collect {
                viewModel.favouriteAdapter?.clearDataList()
                viewModel.favouriteAdapter?.addLists(it)
                viewModel.isDisplayNoData.set(viewModel.favouriteAdapter?.itemCount == 0)
                viewModel.noDataAvailableMsg.set(getString(R.string.no_movies))
            }
        }
    }

    override fun setUpListener() {

    }

    override fun onFavoriteClick(data: MovieInfo, position: Int, view: ImageView) {
        binding.root.shortSnack("Movie is ${if (data.likeOrNot == 1) "Added to" else  "Remove From"} Favorite")
        viewModel.insertOrDeleteMovieInfo(data)
    }

    override fun onItemClick(data: MovieInfo, position: Int) {
        viewModel.currentSelectedItem = position
        val intent = Intent(baseActivity, SecondActivity::class.java)
        intent.putExtra(Constants.MOVIES_INFO, data)
        intent.putExtra(Constants.CURRENT_INDEX, position)
        startActivityForResult(intent, 13)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 13 && resultCode == Activity.RESULT_OK) {
            val index = data?.getIntExtra(Constants.CURRENT_INDEX, 0) ?: 0
            val isFavorite = data?.getBooleanExtra(Constants.CHECK_FAVOURITE, false) ?: false
            parentModel.isFromMovies.value = Pair(index, isFavorite)
        }
    }

    override fun isShare(): Boolean = false
    override fun getModel(): Class<FavoriteViewModel> = FavoriteViewModel::class.java
    override fun getLayout(): Int = R.layout.fragment_favorites
}
