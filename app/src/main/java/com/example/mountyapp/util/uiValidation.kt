package com.example.mountyapp.util
import android.content.Context
import android.net.ConnectivityManager
import android.text.TextUtils
import android.util.Patterns

class uiValidation {

    private val context: Context? = null

    fun isValidEmail(target: String): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    fun isValidPass(pass: String): Boolean {
        return pass.length > 3

    }
    fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}