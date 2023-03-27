package com.example.productapp.viewodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.productapp.data.model.User
import com.example.productapp.utils.showToast
import com.example.productapp.view.activity.SignUpActivity
import com.kotlin.employee.service.db.UserDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor():ViewModel(){
    var isRegistrationSuccessful=MutableLiveData<Boolean>()

    fun registerUser(username: String, email: String,  password: String,  signUpActivity:SignUpActivity) {
        val user = User(0, username, password, email, false)
        val userDatabase = UserDatabase.getInstance(signUpActivity)
        userDatabase.userDao().insertUser(user)

        isRegistrationSuccessful.postValue(false)
    }
}
