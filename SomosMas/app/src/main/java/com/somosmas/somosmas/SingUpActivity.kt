package com.somosmas.somosmas

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.Toast
import androidx.core.util.PatternsCompat
import androidx.core.view.isEmpty
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.somosmas.somosmas.databinding.ActivitySingUpBinding
import java.util.regex.Pattern
import javax.security.auth.login.LoginException

class SingUpActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySingUpBinding
    private lateinit var singUpViewModel : SingUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = binding.txtName
        val email = binding.txtEmail
        val password = binding.txtPassword
        val confirm_password = binding.txtConfirmPassword
        var btn_singUp = binding.btnSingUp

        singUpViewModel = ViewModelProvider(this).get(SingUpViewModel::class.java)
        singUpViewModel.viewStates.observe(this,::handleViewState)


        btn_singUp.setOnClickListener {
            singUpViewModel.validateFields(name.text.toString(), email.text.toString(), password.text.toString(), confirm_password.text.toString())
        }
    }


    private fun handleViewState(viewState: SingUpViewStates){
        var btn_singUp = binding.btnSingUp
        when(viewState){
            is SingUpViewStates.FieldErrorName -> binding.InputName.error = " "
            is SingUpViewStates.FieldErrorEmail -> binding.InputEmail.error = " "
            is SingUpViewStates.FieldErrorPassword -> binding.InputPassword.error = " "
            is SingUpViewStates.FieldErrorConfirmPassword -> binding.InputConfirmPassword.error = "The passwords are not the same"
            is SingUpViewStates.FieldSucces -> toast(this,"Succes") //INTENT A HOME
        }
    }


    fun Context.toast(context: Context = applicationContext, message: String, duration: Int = Toast.LENGTH_SHORT){
        Toast.makeText(context, message, duration).show()
    }
}