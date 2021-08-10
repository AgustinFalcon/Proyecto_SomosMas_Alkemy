package com.somosmas.somosmas

import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SingUpViewModel: ViewModel() {

    private val _viewState = MutableLiveData<SingUpViewStates>()
    val viewStates : LiveData<SingUpViewStates>get() = _viewState


    fun validateFields(name: String, email: String, pw1: String, pw2: String){
        var fieldsOk: Boolean = true

        if(name.isEmpty()){
            _viewState.value = SingUpViewStates.FieldErrorName
            fieldsOk = false
        }else{
            _viewState.value = SingUpViewStates.SuccesName
        }
        if(email.isEmpty() || !PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
            _viewState.value = SingUpViewStates.FieldErrorEmail
            fieldsOk = false
        }else{
            _viewState.value = SingUpViewStates.SuccesEmail
        }
        if(pw1.isEmpty() || !pw1.contains("\\d+(?:\\.\\d+)?".toRegex()) ){
            _viewState.value = SingUpViewStates.FieldErrorPassword
            fieldsOk = false
        }else{
            _viewState.value = SingUpViewStates.SuccesPassword
        }

        if (pw2.isEmpty()){
            _viewState.value = SingUpViewStates.FieldErrorConfirmPassword
            fieldsOk = false
        }else{
            _viewState.value = SingUpViewStates.SuccesConfirmPassword
        }

        if(pw1 != pw2){
            _viewState.value = SingUpViewStates.FieldErrorConfirmPasswordMessage
            fieldsOk = false
        }

        if(fieldsOk){
            _viewState.value = SingUpViewStates.FieldSucces
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
    object FieldSucces: SingUpViewStates()
}