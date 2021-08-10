package com.somosmas.somosmas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.somosmas.somosmas.databinding.ActivityMainBinding
import com.somosmas.somosmas.viewmodel.LoginViewModel
import com.somosmas.somosmas.viewmodel.ViewStates

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: LoginViewModel

    private val URL: String = "http://ongapi.alkemy.org/api/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofitConnection = RetrofitClient().getRetrofit()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.viewState.observe(this, ::handleViewStates)
        viewModel.viewStateBtn.observe(this, ::handleBtnStates)

        binding.inputEmail.addTextChangedListener {
            viewModel.validateInputEmail(binding.inputEmail.text.toString())
        }

        binding.inputPassword.addTextChangedListener {
            viewModel.validatePassword(binding.inputPassword.text.toString())
        }
        //NAVEGACIÓN DESDE LOGIN A SING UP
        binding.btnSingUp.setOnClickListener {
            val intent = Intent(this, SingUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun handleViewStates(viewStates: ViewStates) {
        when (viewStates) {
            is ViewStates.ErrorEmail -> {

                binding.fatherInputEmail.setHelperTextEnabled(true)

                binding.fatherInputEmail.setHelperText("Required")

            }
            is ViewStates.ErrorInvalidEmail -> {

                binding.fatherInputEmail.setHelperTextEnabled(true)

                binding.fatherInputEmail.setHelperText("Invalid email, try again")

            }
            is ViewStates.succesEmail -> {
                if (binding.fatherInputEmail.isHelperTextEnabled) {
                    binding.fatherInputEmail.setHelperTextEnabled(false)
                }
            }
            is ViewStates.ErrorPassword -> {

                binding.fatherInputPassword.setHelperTextEnabled(true)

                binding.fatherInputPassword.setHelperText("Required")

            }
            is ViewStates.succesPassword -> {
                if (binding.fatherInputPassword.isHelperTextEnabled) {

                    binding.fatherInputPassword.setHelperTextEnabled(false)
                }
            }

        }
    }

    private fun handleBtnStates(viewStates: ViewStates) {
        when (viewStates) {
            is ViewStates.blockedBtn -> {
                binding.btnLogin.setEnabled(false)
            }
            is ViewStates.succesBtn -> {
                binding.btnLogin.setEnabled(true)
            }
        }
    }
}