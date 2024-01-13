package com.daniel.budgetplanner.data.sharedpreferences

interface AppPreference {

    fun getString(key:String):String?
    fun setString(key:String,value:String)

    fun getBoolean(key:String):Boolean?
    fun setBoolean(key:String,value:Boolean)

    fun removeValue()
    fun deleteSingleValue(key: String)

}