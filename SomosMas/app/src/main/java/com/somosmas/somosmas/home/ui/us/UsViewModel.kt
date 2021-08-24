package com.somosmas.somosmas.home.ui.us

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is nosotros Fragment"
    }
    val text: LiveData<String> = _text
}