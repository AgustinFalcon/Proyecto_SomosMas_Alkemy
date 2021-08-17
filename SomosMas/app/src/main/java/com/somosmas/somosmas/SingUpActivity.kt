package com.somosmas.somosmas

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.somosmas.somosmas.databinding.ActivitySingUpBinding
import com.somosmas.somosmas.singUpViewModel.*



class SingUpActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySingUpBinding
    private lateinit var singUpViewModel : SingUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySingUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        singUpViewModel = ViewModelProvider(this).get(SingUpViewModel::class.java)
        singUpViewModel.viewStates.observe(this, ::handleViewState)


        binding.txtName.addTextChangedListener {
            singUpViewModel.validateName(binding.txtName.text.toString())
        }

        binding.txtEmail.addTextChangedListener {
            singUpViewModel.validateEmail(binding.txtEmail.text.toString())
        }

        binding.txtPassword.addTextChangedListener {
            singUpViewModel.validatePassword(binding.txtPassword.text.toString())
        }

        binding.txtConfirmPassword.addTextChangedListener {
            singUpViewModel.validateConfirmPassword(binding.txtPassword.text.toString(), binding.txtConfirmPassword.text.toString())
        }
    }


    private fun handleViewState(viewState: SingUpViewStates){
        when(viewState){
            is SingUpViewStates.FieldErrorName -> binding.inputName.error = " "
            is SingUpViewStates.SuccesName -> binding.inputName.error = null
            is SingUpViewStates.FieldErrorEmail -> binding.inputEmail.error = " "
            is SingUpViewStates.SuccesEmail -> binding.inputEmail.error = null
            is SingUpViewStates.FieldErrorPassword -> binding.inputPassword.error = " "
            is SingUpViewStates.SuccesPassword -> binding.inputPassword.error = null
            is SingUpViewStates.FieldErrorConfirmPassword -> binding.inputConfirmPassword.error = " "
            is SingUpViewStates.FieldErrorConfirmPasswordMessage -> binding.inputConfirmPassword.error = "Passwords are not the same"
            is SingUpViewStates.SuccesConfirmPassword -> binding.inputConfirmPassword.error = null
            is SingUpViewStates.btnSucces -> binding.btnSingUp.isEnabled = true
            is SingUpViewStates.btnError -> binding.btnSingUp.isEnabled = false
        }
    }


        fun Context.toast(context: Context = applicationContext, message: String, duration: Int = Toast.LENGTH_SHORT){
            Toast.makeText(context, message, duration).show()
        }
    
}