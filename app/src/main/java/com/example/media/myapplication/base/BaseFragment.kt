package com.example.media.myapplication.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProvider
import com.example.media.myapplication.util.longSnack
import com.example.media.myapplication.util.shortSnack


/**
 * Created by Harshal Chaudhari on 16/3/19.
 */

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment(), LifecycleObserver, BaseErrorCallback {

    lateinit var baseActivity: BaseActivity<*, *>
    open lateinit var binding: T
    open lateinit var viewModel: V



    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseActivity = context as BaseActivity<*, *>
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        viewModel = if (isShare()) ViewModelProvider(baseActivity).get(getModel()) else ViewModelProvider(this).get(getModel())
        viewModel.setCallBack(this)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
        return binding.root
    }

    protected fun setHomeEnabled(value: Boolean) {
        baseActivity.supportActionBar?.setDisplayHomeAsUpEnabled(value)
        baseActivity.supportActionBar?.setHomeButtonEnabled(value)
    }

    override fun onError401(message: String) {
        viewModel.goneProgress.set(true)
    }

    override fun onNetworkChanged(isChanged: Boolean, message: String) {
        viewModel.goneProgress.set(true)
        binding.root.shortSnack(message)
    }

    override fun onWithOrWithoutErrorBody(message: String) {
        viewModel.goneProgress.set(true)
        binding.root.longSnack(message)
    }


    protected abstract fun setUpUi()
    protected abstract fun setUpListener()
    protected abstract fun getModel(): Class<V>
    protected abstract fun isShare(): Boolean
    protected abstract fun getLayout(): Int
}
