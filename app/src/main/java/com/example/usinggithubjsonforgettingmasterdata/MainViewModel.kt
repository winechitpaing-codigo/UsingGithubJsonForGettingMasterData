package com.example.usinggithubjsonforgettingmasterdata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usinggithubjsonforgettingmasterdata.event.Event
import com.squareup.moshi.Moshi
import java.net.URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel : ViewModel() {
    private val moshi: Moshi = Moshi.Builder().build()

    private val _uiEvent = MutableLiveData<Event<MasterData>>()
    val uiEvent: LiveData<Event<MasterData>>
        get() = _uiEvent

    init {
        getMasterData()
    }
    private fun getMasterData() {
        viewModelScope.launch(Dispatchers.IO) {
            val json = URL(BuildConfig.JSON_URL).readText()
            val masterData = moshi.adapter(MasterData::class.java).fromJson(json)!!
            _uiEvent.postValue(Event(masterData))
            Timber.i("MasterData:", masterData.toString())
        }
    }
}