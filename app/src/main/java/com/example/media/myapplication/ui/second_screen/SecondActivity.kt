package com.example.media.myapplication.ui.second_screen

import android.view.View
import com.example.media.myapplication.R
import com.example.media.myapplication.base.BaseActivity
import com.example.media.myapplication.databinding.ActivitySecondBinding
import com.example.media.myapplication.util.Constants
import com.example.media.myapplication.util.animateView
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs


class SecondActivity : BaseActivity<ActivitySecondBinding, SecondViewModel>() {

    override fun setUpUi() {
        setToolbar(binding.toolbar)
        setHomeEnabled(true)
    }

    override fun setUpListener() {
        intent?.let {
            viewModel.movieInfo = it.getParcelableExtra(Constants.MOVIES_INFO)
            viewModel.moviesData = it.getParcelableExtra(Constants.MOVIES)

            when {
                viewModel.movieInfo != null  -> binding.progress = viewModel.movieInfo?.totalVote.toString()
                viewModel.moviesData != null -> binding.progress = viewModel.moviesData?.totalPercent.toString()
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
    }

    override fun getModel(): Class<SecondViewModel> = SecondViewModel::class.java

    override fun getLayoutId(): Int = R.layout.activity_second
}
