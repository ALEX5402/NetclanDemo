package com.android.netclandemo.ui.navigation.screens.explorescreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ExploreViewmodel @Inject constructor(): ViewModel()  {
    private val _getcurrentindex = MutableLiveData<String>()

    // profile
    private val _starchvalueP  = MutableLiveData<String>()
    val serchvalueP : MutableLiveData<String>  = _starchvalueP
    suspend fun updatesearchP(input : String?){
        input?.let {
            viewModelScope.launch {
                _starchvalueP.value = it
            }
        }
    }

    // service
    private val _starchvalueS  = MutableLiveData<String>()
    val serchvalueS : MutableLiveData<String>  = _starchvalueS
    suspend fun updatesearchS(input : String?){
        input?.let {
            viewModelScope.launch {
                _starchvalueS.value = it
            }
        }
    }

    // business
    private val _starchvalueB  = MutableLiveData<String>()
    val serchvalueB : MutableLiveData<String>  = _starchvalueB
    suspend fun updatesearchB(input : String?){
        input?.let {
            viewModelScope.launch {
                _starchvalueB.value = it
            }
        }
    }


    private val _isRefreshing = MutableStateFlow(false)
    private val _isloading = MutableStateFlow(false)

    val isRefreshing: StateFlow<Boolean> = _isRefreshing
    val isloading: StateFlow<Boolean> = _isloading

    fun refreshData() {
        _isRefreshing.value = true
        _isloading.value = true
        viewModelScope.launch {
            withContext(Dispatchers.Main){
                kotlinx.coroutines.delay(2000)  // simulate a network request
                _isRefreshing.value = false
                _isloading.value = false
            }
        }
    }
}