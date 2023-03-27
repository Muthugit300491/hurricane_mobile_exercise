package com.example.productapp.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.productapp.databinding.ActivitySignUpBinding
import com.example.productapp.viewodel.SignupViewModel
import com.kotlin.employee.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    private  lateinit var activitySignUpBinding: ActivitySignUpBinding
    private  val  signupViewMoel: SignupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySignUpBinding=ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(activitySignUpBinding.root)



        activitySignUpBinding.tvSignin.setOnClickListener{
            val intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        signupViewMoel.isRegistrationSuccessful.observe(this, Observer {
            val intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        })

        activitySignUpBinding.btSignup.setOnClickListener {

            val username = activitySignUpBinding.etUsername.text.toString()
            val password = activitySignUpBinding.etPasswd.text.toString()
            val email = activitySignUpBinding.etMail.text.toString()



            if (username.isNotEmpty() && email.isNotEmpty() &&  password.isNotEmpty()) {
                val is_Email = checkRegex(Utils.EMAILID, email)
                val is_Username=checkRegex("",username)
                if (is_Email && is_Username&& password.length==6 ) {
                    signupViewMoel?.registerUser(username, email,  password,this)
                } else if (!is_Email) {
                    activitySignUpBinding.etMail.error = "Invalid Email ID"
                } else if(!is_Username)  {
                    activitySignUpBinding.etUsername.error = "Invalid Username"
                }else {
                    activitySignUpBinding.etPasswd.error = "Invalid Password"
                }
            } else {
                activitySignUpBinding.tvMail.error = "Invalid Email ID"
                activitySignUpBinding.tvUsername.error = "Invalid Username"
                activitySignUpBinding.tvPasswd.error = "Invalid Password"
            }

        }
    }

    fun checkRegex(field_value: String, inputvalue: String): Boolean {
        var is_Check = false
        if (field_value.equals(Utils.EMAILID)) {
            val email_pattern = Pattern.compile(Utils.EMAIL_REGEX)
            is_Check = email_pattern.matcher(inputvalue).matches()
        } else {
            val uname_pattern = Pattern.compile(Utils.USERREGEX)
             is_Check=if(inputvalue.length>=3 && uname_pattern.matcher(inputvalue).matches()) true else false

        }
        return is_Check
    }
}