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

        val name = binding.txtName.text.toString()
        val email = binding.txtEmail.text.toString()
        val password = binding.txtPassword.text.toString()
        val confirm_password = binding.txtConfirmPassword.text.toString()
        var btn_singUp = binding.btnSingUp

        singUpViewModel = ViewModelProvider(this).get(SingUpViewModel::class.java)
        singUpViewModel.viewStates.observe(this,::handleViewState)

        btn_singUp.setOnClickListener {
            singUpViewModel.validateFields(name, email, password, confirm_password)
        }
        //btn_singUp.isEnabled = !(name.isEmpty() && email.isEmpty() && password.isEmpty() && confirm_password.isEmpty())
    }


    private fun handleViewState(viewState: SingUpViewStates){
        var btn_singUp = binding.btnSingUp
        when(viewState){
            is SingUpViewStates.FieldErrorName -> binding.InputName.error = " "
            is SingUpViewStates.FieldErrorEmail -> binding.InputEmail.error = " "
            is SingUpViewStates.FieldErrorPassword -> binding.InputPassword.error = " "
            is SingUpViewStates.FieldErrorConfirmPassword -> binding.InputConfirmPassword.error = "The passwords are not the same"
            is SingUpViewStates.FieldSucces -> toast(this,"Succes")//btn_singUp.isEnabled = true

        }
    }


    fun Context.toast(context: Context = applicationContext, message: String, duration: Int = Toast.LENGTH_SHORT){
        Toast.makeText(context, message, duration).show()
    }
}