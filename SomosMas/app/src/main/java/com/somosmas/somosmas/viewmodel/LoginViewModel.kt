package com.somosmas.somosmas.viewmodel

import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.somosmas.somosmas.model.LoginModel

class LoginViewModel : ViewModel() {
    private val _viewState = MutableLiveData<ViewStates>()
    private val _btnState = MutableLiveData<ViewStates>()

    val viewState: LiveData<ViewStates>
        get() = _viewState

    val viewStateBtn: LiveData<ViewStates>
        get() = _btnState


    fun validateInputEmail(input: String) {
        if (input.isEmpty()) {
            _viewState.value = ViewStates.ErrorEmail
        } else {
            if (!PatternsCompat.EMAIL_ADDRESS.matcher(input).matches()) {
                _viewState.value = ViewStates.ErrorInvalidEmail
                _btnState.value = ViewStates.blockedBtn
            } else {
                _viewState.value = ViewStates.succesEmail
                _btnState.value = ViewStates.succesBtn
            }
        }
    }

    fun validatePassword(input: String) {
        if (input.isEmpty()) {
            _viewState.value = ViewStates.ErrorPassword
            _btnState.value = ViewStates.blockedBtn
        } else {
            _viewState.value = ViewStates.succesPassword
            _btnState.value = ViewStates.succesBtn
        }
    }
}


sealed class ViewStates {
    object ErrorEmail : ViewStates()
    object ErrorInvalidEmail : ViewStates()
    object succesEmail : ViewStates()
    object ErrorPassword : ViewStates()
    object succesPassword : ViewStates()
    object blockedBtn : ViewStates()
    object succesBtn : ViewStates()
}
