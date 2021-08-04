package com.somosmas.somosmas

import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class SingUpViewModel: ViewModel() {
    private val _viewState = MutableLiveData<SingUpViewStates>()
    val viewStates : LiveData<SingUpViewStates>get() = _viewState

    private var fieldsOk: Boolean = true
    val passwordFormat = Pattern.compile("(?=.*[0-9])") //Indica que por lo menos debe haber un numero

    val numeros = arrayListOf(1,2,3,4,5,6,7,8,9)

    fun validateFields(name: String, email: String, pw1: String, pw2: String){

        if(name.isEmpty()){
            _viewState.value = SingUpViewStates.FieldError
            fieldsOk = false
        }
        if(email.isEmpty() || !PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
            _viewState.value = SingUpViewStates.FieldError
            fieldsOk = false
        }
        if(pw1.isEmpty() || !pw1.contains("\\d+(?:\\.\\d+)?".toRegex()) ){
            _viewState.value = SingUpViewStates.FieldError
            fieldsOk = false
        }
        if(pw1 != pw2){
           _viewState.value = SingUpViewStates.FieldError
           fieldsOk = false
        }
        if(fieldsOk){
            _viewState.value = SingUpViewStates.FieldSucces
        }
    }
}



sealed class SingUpViewStates{
    object FieldError: SingUpViewStates()
    object FieldSucces: SingUpViewStates()
}