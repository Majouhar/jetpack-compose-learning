package com.majou.composelearning

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    val textFieldState =  MutableLiveData("")
    fun onTextChanged(value:String){
        textFieldState.value = value
    }

}