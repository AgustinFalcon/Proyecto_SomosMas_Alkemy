package com.somosmas.somosmas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.somosmas.somosmas.home.HomeActivity
import com.somosmas.somosmas.home.ui.home.HomeFragment


class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME: Long = 5000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_TIME)
    }
}