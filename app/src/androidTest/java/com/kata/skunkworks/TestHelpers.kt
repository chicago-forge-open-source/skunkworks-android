package com.kata.skunkworks

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder

val PREFS_KEY = "com.kata.skunkworks"
val LIST_KEY = "skunkworksList"

fun createSharedPrefs(context: Context): SharedPreferences {
    return context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)
}

fun putStringSharedPrefs(editor: SharedPreferences.Editor, value: String) {
    editor.putString(LIST_KEY, value)
    editor.commit()
}

fun putListOfSkunkWorksSharedPrefs(editor: SharedPreferences.Editor, list: List<SkunkWork>) {
    val gson = GsonBuilder().setPrettyPrinting().create()

    editor.putString(LIST_KEY, list.map{gson.toJson(it)}.joinToString(";"))
    editor.commit()
}

fun clearSharedPrefs(editor: SharedPreferences.Editor) {
    editor.clear()
    editor.commit()
}

fun getSkunkWorksFromSharedPrefs(prefs : SharedPreferences) : List<SkunkWork> {
    val gson = Gson()
    val list = (prefs.getString(LIST_KEY, "") ?: "").split(";")
    return list.map{ gson.fromJson(it, SkunkWork::class.java) }
}
//    map(::SkunkWork) == { SkunkWork(it) }
//    map(SkunkWork::title) == {skunkwork -> skunkwork.title }
