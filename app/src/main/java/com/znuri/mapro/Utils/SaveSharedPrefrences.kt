package com.znuri.mapro.Utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

open class SaveSharedPrefrences {
    companion object {
        val TOKEN = ""
    }


}
fun getPreferences(context: Context): SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(context)
}

fun setToken(context: Context, token: String?) {
    var editor: SharedPreferences.Editor = getPreferences(context).edit()
    editor.putString(SaveSharedPrefrences.TOKEN, token)
    editor.apply()
}

fun getToken(context: Context) : String? {
    return getPreferences(context).getString(SaveSharedPrefrences.TOKEN,"")
}