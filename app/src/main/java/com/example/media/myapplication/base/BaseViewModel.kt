package com.example.media.myapplication.base

import android.app.Application
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.media.myapplication.data.room_database.RoomHelper
import com.example.vlcplayer.data.room_database.RoomRepository
import com.google.gson.Gson

abstract class BaseViewModel(application: Application) : AndroidViewModel(application), Observable {

    val mainRepository: MainRepository
    val roomRepository: RoomHelper
    val goneProgress: ObservableBoolean
    val gson = Gson()
    var headerTitle = ObservableField<String>("")
    val isDisplayNoData = ObservableBoolean(false)
    val noDataAvailableMsg = ObservableField<String>("")
    private val callbacks = PropertyChangeRegistry()




    init {
        roomRepository = RoomRepository(application)
        mainRepository = MainRepository(application)
        goneProgress = ObservableBoolean(false)
    }


    fun setCallBack(errorCallback: BaseErrorCallback) {
        mainRepository.setBaseErrorCallback(errorCallback)
    }


    fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null)
    }

    fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

}
