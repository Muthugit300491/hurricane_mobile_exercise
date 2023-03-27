package com.example.productapp.viewodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.productapp.data.model.User
import com.example.productapp.view.activity.LoginActivity
import com.kotlin.employee.service.db.UserDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel  @Inject constructor (): ViewModel() {
    var user: User? = null
    var userDatabase: UserDatabase? = null
    var isLoginSuccessful=MutableLiveData<Boolean>()

    fun validLogin(username: String, password: String,loginActivity: LoginActivity): Unit {
        val userDatabase = UserDatabase.getInstance(loginActivity)
        user = userDatabase.userDao().getUser(username)
        var isValiLogin=false


        user.let {
            if (password.equals(it?.password)) {
                isValiLogin = true
            } else {
                isValiLogin = false
            }
        }
       isLoginSuccessful.postValue(isValiLogin)
    }

}