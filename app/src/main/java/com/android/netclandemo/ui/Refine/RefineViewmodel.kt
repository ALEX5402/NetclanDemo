package com.android.netclandemo.ui.Refine

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class RefineViewmodel @Inject constructor() : ViewModel() {
    private val _StatusText = MutableLiveData<String>()
    val statusText = _StatusText
    var sliderState by mutableFloatStateOf(0f)


    suspend fun updateSlider(value : Float){
        viewModelScope.launch {
            sliderState = value
        }
    }
   suspend fun updateStatustext(input : String?){
        viewModelScope.launch {
            input.let {
                _StatusText.value = input!!
            }
        }
    }


}

