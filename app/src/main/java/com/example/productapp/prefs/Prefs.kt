package com.example.productapp.prefs

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {
    private val prefsFILENAME = "com.aezion.AeRoutingDriver.prefs"
    private var _token = "token"


    val prefs: SharedPreferences = context.getSharedPreferences(prefsFILENAME, 0)

    var mToken: String
        get() = prefs.getString(_token, "")!!
        set(value) = prefs.edit().putString(_token, value).apply()



}