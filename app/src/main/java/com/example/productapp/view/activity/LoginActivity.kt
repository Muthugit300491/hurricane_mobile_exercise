package com.example.productapp.view.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.productapp.R
import com.example.productapp.databinding.ActivityLoginBinding
import com.example.productapp.utils.showToast
import com.example.productapp.utils.toolBar
import com.example.productapp.viewodel.LoginViewModel
import com.kotlin.employee.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var activityLoginBinding: ActivityLoginBinding
    private  val loginViewModel: LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(activityLoginBinding.root)
        toolBar(window, this)
        val sharedPreferences =
            this.getSharedPreferences(Utils.PRODUCTPREFERENCE, Context.MODE_PRIVATE)
        if (sharedPreferences.getString("username", null) != null) {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            // start your next activity
            startActivity(intent)
            finish()
        }

        activityLoginBinding.tvDontAccount.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            // start your next activity
            startActivity(intent)
            finish()
        }

        loginViewModel.isLoginSuccessful.observe(this, Observer {
            if (it) {
                val editor = sharedPreferences.edit()
                editor.putString("username", activityLoginBinding.etUsername.text.toString())
                editor.apply()
                editor.commit()
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                // start your next activity
                startActivity(intent)
                finish()
            } else {
                showToast("Invalid Username and Password")
            }

        })

        activityLoginBinding.btLogin.setOnClickListener {
            val username = activityLoginBinding.etUsername.text.toString()
            val password = activityLoginBinding?.etPasswd?.text.toString()
            if (username.isNotEmpty() && password.isNotEmpty()) {
                loginViewModel?.validLogin(username, password,this)
            } else {
                activityLoginBinding.tvUsername.error = "Invalid Username"
                activityLoginBinding.tvPassword.error = "Invalid Password"
            }
        }
    }


}
