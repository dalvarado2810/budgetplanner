package com.daniel.budgetplanner.data.sharedpreferences

import android.content.Context
import com.daniel.budgetplanner.utils.EMPTY_STRING
import com.daniel.budgetplanner.utils.SHARED_PREFERENCE

class SharedPreferencesImpl(context: Context): AppPreference {

    private val  sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE, 0)
    private val  editor = sharedPreferences.edit()

    override fun getString(key: String): String? {
        return sharedPreferences.getString(key, EMPTY_STRING)
    }

    override fun setString(key: String, value: String) {
        editor.putString(key, value)
        editor.apply()
    }

    override fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, true)
    }

    override fun setBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    override fun removeValue() {
        editor.clear()
        editor.apply()
    }

    override fun deleteSingleValue(key: String) {
        editor.remove(key)
        editor.apply()
    }
}