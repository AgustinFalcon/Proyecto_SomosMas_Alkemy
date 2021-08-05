package com.somosmas.somosmas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofitConnection = RetrofitClient().getRetrofit()

        val btn_singUp = findViewById<Button>(R.id.btnSingUp)

        btn_singUp.setOnClickListener {
            val intent = Intent(this,SingUpActivity::class.java)
            startActivity(intent)
        }
    }

}