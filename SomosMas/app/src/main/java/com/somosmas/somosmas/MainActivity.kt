package com.somosmas.somosmas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.somosmas.somosmas.databinding.ActivityMainBinding
import com.somosmas.somosmas.desingUI.LoadingDialog
import com.somosmas.somosmas.home.HomeActivity
import com.somosmas.somosmas.viewmodel.LoginViewModel
import com.somosmas.somosmas.viewmodel.ViewStates

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.viewState.observe(this, ::handleViewStates)

        viewModel.viewStateBtn.observe(this, ::handleBtnStates)

        //Navegacion desde login a home
        with(binding) {

            inputEmail.addTextChangedListener {
                viewModel.validateInputEmail(inputEmail.text.toString())
            }
            inputPassword.addTextChangedListener {
                viewModel.validatePassword(inputPassword.text.toString())
            }
            btnSingUp.setOnClickListener {
                goToSingUp()
            }
            btnLogin.setOnClickListener {
                showAlert()
            }
        }
    }

    private fun goToSingUp() {
        val intent = Intent(this, SingUpActivity::class.java)
        startActivity(intent)
    }

    private fun goToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun showAlert() {
        val loading = LoadingDialog(this)
        loading.startLoading()
        val handler = Handler()
        handler.postDelayed({
            loading.isDissmiss()
            goToHome()
        }, 5000)
    }

    private fun handleViewStates(viewStates: ViewStates) {
        when (viewStates) {
            is ViewStates.ErrorEmail -> {

                binding.fatherInputEmail.isHelperTextEnabled = true

                binding.fatherInputEmail.helperText = "Required"

            }
            is ViewStates.ErrorInvalidEmail -> {

                binding.fatherInputEmail.isHelperTextEnabled = true

                binding.fatherInputEmail.helperText = "Invalid email, try again"

            }
            is ViewStates.succesEmail -> {
                if (binding.fatherInputEmail.isHelperTextEnabled) {
                    binding.fatherInputEmail.isHelperTextEnabled = false
                }
            }
            is ViewStates.ErrorPassword -> {

                binding.fatherInputPassword.setHelperTextEnabled(true)

                binding.fatherInputPassword.helperText = "Required"

            }
            is ViewStates.succesPassword -> {
                if (binding.fatherInputPassword.isHelperTextEnabled) {

                    binding.fatherInputPassword.isHelperTextEnabled = false
                }
            }

        }
    }

    private fun handleBtnStates(viewStates: ViewStates) {
        when (viewStates) {
            is ViewStates.blockedBtn -> {
                binding.btnLogin.isEnabled = false
            }
            is ViewStates.succesBtn -> {
                binding.btnLogin.isEnabled = true
            }
        }
    }
}