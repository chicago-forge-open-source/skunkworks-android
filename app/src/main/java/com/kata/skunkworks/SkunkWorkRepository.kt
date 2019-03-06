package com.kata.skunkworks

import android.content.Context

class SkunkWorkRepository(val context: Context) {
    fun findAllSkunkWorks(): List<SkunkWork> {
        val sharedPrefs = context.getSharedPreferences("com.kata.skunkworks", Context.MODE_PRIVATE)

        return sharedPrefs.getString("skunkworksList", "").split(",").map(::SkunkWork)
    }

}
