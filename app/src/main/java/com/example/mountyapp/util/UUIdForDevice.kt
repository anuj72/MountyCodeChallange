package com.example.mountyapp.util

import android.content.Context
import android.content.SharedPreferences
import java.util.*


private var uniqueID: String? = null
private const val PREF_UNIQUE_ID = "PREF_UNIQUE_ID"
fun UUid(context: Context): String {
    if (uniqueID == null) {
        val sharedPrefs: SharedPreferences = context.getSharedPreferences(
            PREF_UNIQUE_ID, Context.MODE_PRIVATE
        )
        uniqueID = sharedPrefs.getString(PREF_UNIQUE_ID, null)
        if (uniqueID == null) {
            uniqueID = UUID.randomUUID().toString()
            val editor: SharedPreferences.Editor = sharedPrefs.edit()
            editor.putString(PREF_UNIQUE_ID, uniqueID)
            editor.commit()
        }
    }
    return uniqueID as String
}