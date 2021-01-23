package com.example.mountyapp.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.provider.OpenableColumns
import android.text.TextUtils
import android.util.Patterns
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.mountyapp.R
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import es.dmoral.toasty.Toasty


fun Context.toast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG ).show()
}

fun ProgressBar.show(){
    visibility = View.VISIBLE
}

fun ProgressBar.hide(){
    visibility = View.GONE
}

fun View.snackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("Ok") {
            snackbar.dismiss()
        }
    }.show()
}

/*
fun Context.LogoutBox(){
    val builder = AlertDialog.Builder(this)
    var alertDialog: AlertDialog?=null
    //set title for alert dialog
    builder.setTitle("Log out of K8 School")
    //set message for alert dialog
    builder.setMessage("Are you sure you want to log out?")
    builder.setIcon(R.drawable.ic_power_black)
    // Create the AlertDialog

    //performing positive action
    builder.setPositiveButton("Yes"){dialogInterface, which ->
        UserSharedPreference(this).remove()
    }

    builder.setNegativeButton("No"
    ){
            dialogInterface, which ->
    }
    // Set other dialog properties
    alertDialog = builder.create()
    alertDialog.setCancelable(true)
    alertDialog.show()
    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#FF8600"))
    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#FF8600"))
}
*/



fun Context.SuccessToast(message: String){
    Toasty.success(applicationContext  , message, Toast.LENGTH_SHORT, true).show()
}

fun Context.ErrorToast(message: String){
    Toasty.error(applicationContext  , message, Toast.LENGTH_SHORT, true).show()
}





fun ContentResolver.getFileName(fileUri: Uri): String {
    var name = ""
    val returnCursor = this.query(fileUri, null, null, null, null)
    if (returnCursor != null) {
        val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        returnCursor.moveToFirst()
        name = returnCursor.getString(nameIndex)
        returnCursor.close()
    }
    return name
}


fun CircleImageView.load(resId: String, centerCrop: Boolean = true, fit: Boolean = true) {
    Picasso.get()
        .load(resId)
        .also { if (centerCrop) it.centerCrop() }
        .also { if (fit) it.fit() }
        .placeholder(R.drawable.user)
        .error(R.drawable.user)
        .into(this)
}

fun Any?.toNotNull(): String {
    if (this == null) return "NA"
    if (this.toString().trim() == "") return "NA"
    if (this.toString().trim() == "null") return "NA"
    // after the null check, 'this' is autocast to a non-null type, so the toString() below
    // resolves to the member function of the Any class
    return toString()

}
fun Any?.toEmpty(): String? {
    if (this == null) return ""
    if (this.toString().trim() == "") return ""
    if (this.toString().trim() == "null") return ""
    // after the null check, 'this' is autocast to a non-null type, so the toString() below
    // resolves to the member function of the Any class
    return toString()

}
fun Any?.isValidEmail(): Boolean {
    return !TextUtils.isEmpty(this.toEmpty()) && Patterns.EMAIL_ADDRESS.matcher(this.toEmpty()).matches()
}



fun Int.abs() : Int{
    return if(this < 0) -this else this
}


    fun hideKeyboard(activity: Activity) { // Check if no view has focus:
        val view = activity.currentFocus
        if (view != null) {
            val inputManager: InputMethodManager =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

@SuppressLint("ResourceAsColor")
@ColorInt
fun Context.getColorResCompat(@AttrRes id: Int): Int {
    val resolvedAttr = TypedValue()
    this.theme.resolveAttribute(id, resolvedAttr, true)
    val colorRes = resolvedAttr.run { if (resourceId != 0) resourceId else data }
    return ContextCompat.getColor(this, colorRes)
}
