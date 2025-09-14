package com.abcd.dicodingsubmissionbelajarandroidinteremediate.ui.activity.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.abcd.dicodingsubmissionbelajarandroidinteremediate.MainActivity
import com.abcd.dicodingsubmissionbelajarandroidinteremediate.RegisterActivity
import com.abcd.dicodingsubmissionbelajarandroidinteremediate.R
import com.abcd.dicodingsubmissionbelajarandroidinteremediate.data.factory.LoginFactory
import com.abcd.dicodingsubmissionbelajarandroidinteremediate.data.model.UserModel
import com.abcd.dicodingsubmissionbelajarandroidinteremediate.databinding.ActivityLoginBinding
import com.abcd.dicodingsubmissionbelajarandroidinteremediate.utils.LoginPreferences
import com.abcd.dicodingsubmissionbelajarandroidinteremediate.utils.datStore
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setViewModel()
        setupView()
        setUpAction()
    }

    private fun setViewModel(){
        val preferences = LoginPreferences.getInstance(application.datStore)
        viewModel = ViewModelProvider(this@LoginActivity, LoginFactory(preferences)).get(
            LoginViewModel::class.java
        )

        viewModel.getLogin().observe(this@LoginActivity){result->
            result.isLogin?.let {
                if(it){
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                }
            }
        }

    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setUpAction(){
        binding.apply {
            btnLogin.setOnClickListener {
                if(checkEditText()){
                    val email = etEmail.text.toString()
                    val password = etPassword.text.toString()

                    viewModel.saveLogin(
                        UserModel(
                            email, password, true
                        )
                    )

                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                }
            }
            tvRegistrasi.setOnClickListener{
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
        }
    }

    private fun checkEditText(): Boolean{
        binding.apply {
            if(etEmail.text.isNullOrEmpty()) return setError(etEmail)
            if(etPassword.text.isNullOrEmpty()) return setError(etPassword)
        }
        return true
    }

    private fun setError(editText: TextInputEditText): Boolean{
        editText.error = getString(R.string.message_error)

        return false
    }
}