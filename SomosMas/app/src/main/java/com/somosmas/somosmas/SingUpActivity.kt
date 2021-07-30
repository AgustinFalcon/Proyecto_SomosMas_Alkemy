package com.somosmas.somosmas

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.core.util.PatternsCompat
import com.google.android.material.textfield.TextInputLayout
import com.somosmas.somosmas.databinding.ActivitySingUpBinding
import java.util.regex.Pattern

class SingUpActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySingUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //PARA NO TENER LA BARRA DE NOTIFICACIONES DE TITULO
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
/*
        val txt_nomre_usuario = findViewById<TextInputLayout>(R.id.txt_name)
        val txt_email = findViewById<TextInputLayout>(R.id.txt_email)
        val txt_password = findViewById<TextInputLayout>(R.id.txt_password)
        val txt_confirm_password = findViewById<TextInputLayout>(R.id.txt_confirm_password)
        val btn_singUp = findViewById<Button>(R.id.btn_singUp)

        btn_singUp.visibility = View.VISIBLE

        btn_singUp.setOnClickListener {

        }*/
    }


    private fun validarName() : Boolean{
        val name = binding.txtName.editText?.text.toString()
        return if(name.isEmpty()){
            binding.txtName.error = "Field can not be empty"
            false
        } else {
            binding.txtName.error = null
            true
        }
    }

    private fun validarEmail() : Boolean{
        val email = binding.txtEmail.editText?.text.toString()
        return if (email.isEmpty()){
            binding.txtEmail.error = "Field can not be empty"
            false
        } else if(!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
            binding.txtEmail.error = "Please enter a valid email addres"
            false
        } else {
            binding.txtEmail.error = null
            true
        }
    }


    private fun validarPassword() : Boolean{
        val password = binding.txtPassword.editText?.text.toString()
        val passwordFormat = Pattern.compile("(?=.*[0-9])") //Indica que por lo menos debe haber un numero
        return if (password.isEmpty()){
            binding.txtPassword.error = "Field can not be empty"
            false
        } else if(!passwordFormat.matcher(password).matches()){
            binding.txtPassword.error = "The password should have one o more numbers"
            false
        } else {
            binding.txtPassword.error = null
            true
        }
    }

    private fun validate(){
        val result = arrayOf(validarName(),validarEmail(),validarPassword())

        if (false in result){
            return
        }
        toast(this,"Succes")
    }

    fun Context.toast(context: Context = applicationContext, message: String, duration: Int = Toast.LENGTH_SHORT){
        Toast.makeText(context, message, duration).show()
    }
}