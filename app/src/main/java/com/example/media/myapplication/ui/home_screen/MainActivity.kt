package com.example.media.myapplication

import com.example.media.myapplication.base.BaseActivity
import com.example.media.myapplication.databinding.ActivityMainBinding
import com.example.media.myapplication.ui.main.SectionsPagerAdapter


class MainActivity : BaseActivity<ActivityMainBinding, ActivityViewModel>() {

    override fun setUpListener() {

    }

    override fun setUpUi() {
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }

    override fun getModel(): Class<ActivityViewModel> = ActivityViewModel::class.java

    override fun getLayoutId(): Int = R.layout.activity_main
}