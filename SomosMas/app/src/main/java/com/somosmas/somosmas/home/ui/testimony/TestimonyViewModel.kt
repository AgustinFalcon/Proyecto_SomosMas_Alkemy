package com.somosmas.somosmas.home.ui.testimony

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TestimonyViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is testimonios Fragment"
    }
    val text: LiveData<String> = _text
}