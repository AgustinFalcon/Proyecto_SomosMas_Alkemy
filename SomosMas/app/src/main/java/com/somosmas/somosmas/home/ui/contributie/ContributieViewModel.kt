package com.somosmas.somosmas.home.ui.contributie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContributieViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is contribuye Fragment"
    }
    val text: LiveData<String> = _text
}