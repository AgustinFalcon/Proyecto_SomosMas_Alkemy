package com.somosmas.somosmas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.os.Handler
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.somosmas.somosmas.databinding.ActivityMainBinding
import com.somosmas.somosmas.desingUI.CustomDialogFragment
import com.somosmas.somosmas.desingUI.LoadingDialog
import com.somosmas.somosmas.home.HomeActivity
import com.somosmas.somosmas.viewmodel.LoginViewModel
import com.somosmas.somosmas.viewmodel.ViewStates

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: LoginViewModel

    private val URL: String = "http://ongapi.alkemy.org/api/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //INTENT DE LOGIN A SING UP
        binding.btnSingUp.setOnClickListener {
            val intent = Intent(this, SingUpActivity::class.java)
            startActivity(intent)
        }


        val loadin=LoadingDialog(this)
        loadin.startLoading()
        val handler=Handler()
        handler.postDelayed(object :Runnable{
            override fun run() {
                loadin.isDissmiss()
            }
        }, 5000)


        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.viewState.observe(this, ::handleViewStates)

        viewModel.viewStateBtn.observe(this, ::handleBtnStates)

        binding.inputEmail.addTextChangedListener {
            viewModel.validateInputEmail(binding.inputEmail.text.toString())
        }

        binding.inputPassword.addTextChangedListener {
            viewModel.validatePassword(binding.inputPassword.text.toString())
        }

        //Navegacion desde login a sing up
        binding.btnSingUp.setOnClickListener {
            val intent = Intent(this, SingUpActivity::class.java)
            startActivity(intent)
        }
        //Navegacion desde login a home
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
    fun showAlert(view: View){
        CustomDialogFragment().show(supportFragmentManager, "Custom dialog fragment")
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