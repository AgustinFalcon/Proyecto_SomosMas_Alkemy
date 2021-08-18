package com.somosmas.somosmas.home.ui.novedades

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NovedadesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is novedades Fragment"
    }
    val text: LiveData<String> = _text
}