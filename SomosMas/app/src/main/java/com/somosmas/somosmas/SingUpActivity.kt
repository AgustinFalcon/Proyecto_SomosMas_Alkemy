package com.somosmas.somosmas

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.somosmas.somosmas.databinding.ActivitySingUpBinding


class SingUpActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySingUpBinding
    private lateinit var singUpViewModel : SingUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySingUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        singUpViewModel = ViewModelProvider(this).get(SingUpViewModel::class.java)
        singUpViewModel.viewStates.observe(this, ::handleViewState)


            binding.btnSingUp.setOnClickListener{
                singUpViewModel.validateFields(
                    binding.txtName.text.toString(),
                    binding.txtEmail.text.toString(),
                    binding.txtPassword.text.toString(),
                    binding.txtConfirmPassword.text.toString())
            }
    }


    private fun handleViewState(viewState: SingUpViewStates){
        when(viewState){
            is SingUpViewStates.FieldErrorName -> binding.InputName.error = " "
            is SingUpViewStates.SuccesName -> binding.InputName.error = null
            is SingUpViewStates.FieldErrorEmail -> binding.InputEmail.error = " "
            is SingUpViewStates.SuccesEmail -> binding.InputEmail.error = null
            is SingUpViewStates.FieldErrorPassword -> binding.InputPassword.error = " "
            is SingUpViewStates.SuccesPassword -> binding.InputPassword.error = null
            is SingUpViewStates.FieldErrorConfirmPassword -> binding.InputConfirmPassword.error = " "
            is SingUpViewStates.FieldErrorConfirmPasswordMessage -> binding.InputConfirmPassword.error = "Passwords are not the same"
            is SingUpViewStates.SuccesConfirmPassword -> binding.InputConfirmPassword.error = null
            is SingUpViewStates.FieldSucces -> toast(this,"Succes") //INTENT A HOME
        }
    }


        fun Context.toast(context: Context = applicationContext, message: String, duration: Int = Toast.LENGTH_SHORT){
            Toast.makeText(context, message, duration).show()
        }
    
}