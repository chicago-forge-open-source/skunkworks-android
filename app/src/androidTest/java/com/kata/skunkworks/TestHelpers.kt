package com.kata.skunkworks

import android.content.Context
import android.content.SharedPreferences

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
    editor.putString(LIST_KEY, list.map(SkunkWork::title).joinToString(","))
    editor.commit()
}

fun clearSharedPrefs(editor: SharedPreferences.Editor) {
    editor.clear()
    editor.commit()
}
