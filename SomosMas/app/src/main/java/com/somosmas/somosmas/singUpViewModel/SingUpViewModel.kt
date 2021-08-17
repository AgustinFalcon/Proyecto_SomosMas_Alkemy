package com.somosmas.somosmas.singUpViewModel

import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SingUpViewModel: ViewModel() {

    private val _viewState = MutableLiveData<SingUpViewStates>()
    val viewStates : LiveData<SingUpViewStates>get() = _viewState

    fun validateName(name: String){
        if(name.isEmpty()){
            _viewState.value = SingUpViewStates.FieldErrorName
            _viewState.value = SingUpViewStates.btnError
        }else{
            _viewState.value = SingUpViewStates.SuccesName
        }
    }

    fun validateEmail(email: String){
        if(email.isEmpty() || !PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
            _viewState.value = SingUpViewStates.FieldErrorEmail
            _viewState.value = SingUpViewStates.btnError
        }else{
            _viewState.value = SingUpViewStates.SuccesEmail
        }
    }

    fun validatePassword(pw1: String){
        if(pw1.isEmpty() || !pw1.contains("\\d+(?:\\.\\d+)?".toRegex()) ){
            _viewState.value = SingUpViewStates.FieldErrorPassword
            _viewState.value = SingUpViewStates.btnError
        }else{
            _viewState.value = SingUpViewStates.SuccesPassword
        }
    }

    fun validateConfirmPassword(pw1: String, pw2: String){
        if (pw2.isEmpty()){
            _viewState.value = SingUpViewStates.FieldErrorConfirmPassword
            _viewState.value = SingUpViewStates.btnError
        }else{
            _viewState.value = SingUpViewStates.SuccesConfirmPassword
        }
        if(pw1 != pw2){
            _viewState.value = SingUpViewStates.FieldErrorConfirmPasswordMessage
            _viewState.value = SingUpViewStates.btnError
        }else{
            _viewState.value = SingUpViewStates.btnSucces
        }
    }
}

sealed class SingUpViewStates{
    object FieldErrorName: SingUpViewStates()
    object SuccesName: SingUpViewStates()
    object FieldErrorEmail: SingUpViewStates()
    object SuccesEmail: SingUpViewStates()
    object FieldErrorPassword: SingUpViewStates()
    object SuccesPassword: SingUpViewStates()
    object FieldErrorConfirmPassword: SingUpViewStates()
    object FieldErrorConfirmPasswordMessage: SingUpViewStates()
    object SuccesConfirmPassword: SingUpViewStates()
    object btnSucces: SingUpViewStates()
    object btnError: SingUpViewStates()
}