package com.example.media.myapplication.data.preferences

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

@SuppressLint("CommitPrefEdits")
class SharedPreference private constructor(val context: Context) {

    private val sharedPreference: SharedPreferences
    private val editor: SharedPreferences.Editor
    private val gson = Gson()

    companion object {
        const val USER_PREFS = "userPrerfs"
    }

    init {
        sharedPreference = context.getSharedPreferences(USER_PREFS, Activity.MODE_PRIVATE)
        editor = sharedPreference.edit()
    }


}

