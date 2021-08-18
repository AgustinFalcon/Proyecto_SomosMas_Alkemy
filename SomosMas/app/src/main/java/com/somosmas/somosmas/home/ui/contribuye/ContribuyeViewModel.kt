package com.somosmas.somosmas.home.ui.contribuye

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContribuyeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is contribuye Fragment"
    }
    val text: LiveData<String> = _text
}