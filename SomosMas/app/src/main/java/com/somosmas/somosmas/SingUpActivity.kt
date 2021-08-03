package com.somosmas.somosmas

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.util.PatternsCompat
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


        val inputName = findViewById<TextInputLayout>(R.id.Input_name)
        val inputEmail = findViewById<TextInputLayout>(R.id.Input_email)
        val inputPassword = findViewById<TextInputLayout>(R.id.Input_password)
        val inputConfirmPassword = findViewById<TextInputLayout>(R.id.Input_confirm_password)


        val password = findViewById<TextInputEditText>(R.id.txt_password)
        val confirm_password = findViewById<TextInputEditText>(R.id.txt_confirme_password)
        val name = findViewById<TextInputEditText>(R.id.txt_name)
        val email = findViewById<TextInputEditText>(R.id.txt_email)

        singUpViewModel = ViewModelProvider(this).get(SingUpViewModel::class.java)
        singUpViewModel.viewStates.observe(this,::handleViewState)

        binding.btnSingUp.setOnClickListener {
            singUpViewModel.validateFields(name.text.toString(),email.text.toString(),password.text.toString(),confirm_password.text.toString())
        }

    }


    private fun handleViewState(viewState: SingUpViewStates){
        when(viewState){
            is SingUpViewStates.FieldError -> toast(this,"Error!")
            is SingUpViewStates.FieldSucces -> toast(this,"Succes!")
        }
    }

/*
    fun validar(name: String, email: String, password: String, confirm_password: String){
        var salir = true
        val name = name
        val email = email
        val password = password
        val confirm_password = confirm_password
        val passwordFormat = Pattern.compile("(?=.*[0-9])") //Indica que por lo menos debe haber un numero

        if (name.isEmpty()){
            binding.txtName.setError("Cannot be empty")
            salir = false
        }
        if (email.isEmpty() || !PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
            binding.txtEmail.setError("Cannot be empty")
            salir = false
        }
        if (password.isEmpty() && !passwordFormat.matcher(password).matches()){
            binding.txtPassword.setError("Cannot be empty")
            salir = false
        }
        if (password!=confirm_password){
            binding.txtConfirmPassword.error = "The passwords are not the same"
            salir = false
        }

        if (salir){
            binding.btnSingUp.setEnabled(true)
        }
    }*/


/*
    private fun validarName(name: String) : Boolean{
        return name.isEmpty()
    }

    private fun validarEmail(email: String) : Boolean{
        return !email.isEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }


    private fun validarPassword(password: String) : Boolean {
        val passwordFormat =
            Pattern.compile("(?=.*[0-9])") //Indica que por lo menos debe haber un numero
        return !password.isEmpty() && passwordFormat.matcher(password).matches()
    }

    private fun validarConfirmPassword(password: String, confirm_password: String) : Boolean{
        return password == confirm_password
    }

    private fun validate(name: String, email: String, password: String, confirm_password: String): Boolean{
        val result = arrayOf(validarName(name), validarEmail(email), validarPassword(password), validarConfirmPassword(password,confirm_password))

        return if (false in result){
            false
        }else{
            toast(this,"Succes")
            binding.btnSingUp.isEnabled
            true
        }
    }*/

    fun Context.toast(context: Context = applicationContext, message: String, duration: Int = Toast.LENGTH_SHORT){
        Toast.makeText(context, message, duration).show()
    }
}