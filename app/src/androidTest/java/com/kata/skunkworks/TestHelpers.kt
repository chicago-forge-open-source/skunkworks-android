package com.kata.skunkworks

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

val PREFS_KEY = "com.kata.skunkworks"
val LIST_KEY = "skunkworksList"
val gson = GsonBuilder().setPrettyPrinting().create()

fun createSharedPrefs(context: Context): SharedPreferences {
    return context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)
}

fun putListOfSkunkWorksSharedPrefs(editor: SharedPreferences.Editor, skunkWorks: List<SkunkWork>) {
    editor.putString(LIST_KEY, gson.toJson(skunkWorks))
    editor.commit()
}

fun clearSharedPrefs(editor: SharedPreferences.Editor) {
    editor.clear()
    editor.commit()
}

fun getSkunkWorksFromSharedPrefs(prefs: SharedPreferences): List<SkunkWork> {
    val skunkWorksString = prefs.getString(LIST_KEY, "") ?: ""
    return gson.fromJson(skunkWorksString, object : TypeToken<MutableList<SkunkWork>>() {}.type)
}