package com.somosmas.somosmas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class sing_up : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)

        val txt_nomre_usuario = findViewById<TextInputLayout>(R.id.txt_name)
        val txt_email = findViewById<TextInputLayout>(R.id.txt_email)
        val txt_password = findViewById<TextInputLayout>(R.id.txt_password)
        val txt_confirm_password = findViewById<TextInputLayout>(R.id.txt_confirm_password)
        val btn_singUp = findViewById<Button>(R.id.btn_singUp)



    }

    fun validar(){

    }
}