package com.example.media.myapplication.ui.second_screen

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.media.myapplication.R
import com.example.media.myapplication.base.BaseActivity
import com.example.media.myapplication.base.BaseRecyclerItemClick
import com.example.media.myapplication.data.model.Cast
import com.example.media.myapplication.databinding.ActivitySecondBinding
import com.example.media.myapplication.util.Constants
import com.example.media.myapplication.util.animateView
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs


class SecondActivity : BaseActivity<ActivitySecondBinding, SecondViewModel>(), BaseRecyclerItemClick<Cast> {

    private var index = 0

    override fun setUpUi() {
        viewModel.castAdapter = CastAdapter(emptyList<Cast>().toMutableList(), this)
        setToolbar(binding.toolbar)
        setHomeEnabled(true)
        listenMutableEvent()
    }

    private fun listenMutableEvent() {
        viewModel.getMovieCredits.observe(this, Observer { data ->
            data?.let {
                viewModel.castAdapter?.addLists(it.cast)
            }
            viewModel.goneProgress.set(true)
        })


        viewModel.getMovieDetail.observe(this, Observer { data ->
            data?.let {
                viewModel.fillAllDetailData(it)
            }
            viewModel.goneProgress.set(true)
        })
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun setUpListener() {
        intent?.let {
            viewModel.movieInfo = it.getParcelableExtra(Constants.MOVIES_INFO)
            index = it.getIntExtra(Constants.CURRENT_INDEX, 0)
            viewModel.callMovieDetailAndCreditApi()
            viewModel.isFavorite = viewModel.movieInfo?.likeOrNot == 1
            binding.contentSecond.ivIsFavorites.setImageDrawable(ContextCompat.getDrawable(binding.root.context, if (viewModel.movieInfo?.likeOrNot == 1) R.drawable.ic_favorite_black_fill_24dp else R.drawable.ic_favorite_border_black_24dp))

            when {
                viewModel.movieInfo != null  -> binding.progress = viewModel.movieInfo?.totalVote.toString()
                else                         -> binding.progress = "0"
            }
        }

        binding.appBar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                val offset = appBarLayout?.getTotalScrollRange() ?: 0
                if (abs(verticalOffset) - offset == 0) {
                    //collapse
                    binding.fab.animateView()
                    binding.fab.visibility = View.GONE
                }
                else {
                    //Expanded
                    binding.fab.animateView()
                    binding.fab.visibility = View.VISIBLE
                }
            }
        })

        binding.contentSecond.ivIsFavorites.setOnClickListener {
            if (!viewModel.isFavorite) {
                viewModel.isFavorite = true
                viewModel.insertOrDeleteMovieInfo()
                binding.contentSecond.ivIsFavorites.setImageResource(R.drawable.like)
                (binding.contentSecond.ivIsFavorites.drawable as AnimatedVectorDrawable).start()
            } else {
                viewModel.isFavorite = false
                viewModel.insertOrDeleteMovieInfo()
                binding.contentSecond.ivIsFavorites.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.ic_favorite_border_black_24dp))
            }
        }
    }

    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra(Constants.CHECK_FAVOURITE, viewModel.isFavorite)
        intent.putExtra(Constants.CURRENT_INDEX, index)
        setResult(Activity.RESULT_OK, intent)
        finish()
        super.onBackPressed()
    }

    override fun getModel(): Class<SecondViewModel> = SecondViewModel::class.java

    override fun getLayoutId(): Int = R.layout.activity_second
}
