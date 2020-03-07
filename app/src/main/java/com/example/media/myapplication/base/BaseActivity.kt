package com.example.media.myapplication.base

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity(), BaseErrorCallback, LifecycleOwner {

    lateinit var binding: T
    lateinit var viewModel: V



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        viewModel = ViewModelProvider(this).get(getModel())
        viewModel.setCallBack(this)
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = this
        setUpUi()
        setUpListener()
    }

    protected fun setToolbar(toolbar: androidx.appcompat.widget.Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    protected fun setHomeEnabled(value: Boolean = false) {
        supportActionBar?.setDisplayHomeAsUpEnabled(value)
        supportActionBar?.setHomeButtonEnabled(value)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item!!)
    }

    override fun onError401(message: String) {
        viewModel.goneProgress.set(true)
    }

    override fun onNetworkChanged(isChanged: Boolean, message: String) {
        viewModel.goneProgress.set(true)
    }

    override fun onWithOrWithoutErrorBody(message: String) {
        viewModel.goneProgress.set(true)
    }

    protected abstract fun setUpUi()
    protected abstract fun setUpListener()
    protected abstract fun getModel(): Class<V>
    protected abstract fun getLayoutId(): Int
}
