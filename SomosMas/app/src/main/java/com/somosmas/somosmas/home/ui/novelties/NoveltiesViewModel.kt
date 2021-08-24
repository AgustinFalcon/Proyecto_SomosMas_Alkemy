package com.somosmas.somosmas.home.ui.novelties

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NoveltiesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Novedades"
    }
    val text: LiveData<String> = _text
}