package com.kotlin.employee.utils

import android.R
import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log



object Utils {

    val EMAIL_REGEX = "^(.+)@(.+)$"
    val MOBILE_REGEX = "^\\d{10}$"
    val USERREGEX="^[a-zA-Z0-9]*$"
    val EMAILID = "email"
    val MOBILE = "mobile"
    val PRODUCTPREFERENCE = "PRODUCT"


    fun isConnected(context: Context): Boolean {
        var connected = false
        try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val nInfo = cm.activeNetworkInfo
            connected = nInfo != null && nInfo.isAvailable && nInfo.isConnected
            return connected
        } catch (e: Exception) {
            Log.e("Connectivity Exception", "" + e.message)
        }
        return connected
    }


   /* fun getProgressDialog(
        activity: Activity,
        toastMsg: String?,
        action: String?,
        cancelable: Boolean
    ): KProgressHUD? {
        return KProgressHUD.create(activity)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setWindowColor(activity.resources.getColor(R.color.transparent))
            .setAnimationSpeed(2)
            .setDimAmount(0.5f)
            .setLabel(toastMsg)
            .setDetailsLabel(action)
            .setCancellable(cancelable)
    }*/

}