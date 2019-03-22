package com.kata.skunkworks

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class SkunkWorkRepository(appContext: Context) {
    private val defaultSkunkWorks : MutableList<SkunkWork> = listOf(
        "Can Beam",
        "Mini Drone Forge Tour Guide",
        "Smart Light That Goes Red When Build Fails",
        "NFC Chip That Gives Wifi Access",
        "NFC Ventra Clothing",
        "Train Set",
        "Nap Pods",
        "DX War Room",
        "Greeting Robot That Recognizes You Based On Key Card",
        "Cool Light For When Creative Collision is Ready",
        "Interactive Room Reservation System",
        "Custom Magnet All The Things",
        "Amiibo Features Around Features Around The Office",
        "Card Wall With NFC Chips"
    ).map{ SkunkWork(it) }.toMutableList()

    private val sharedPrefs = appContext.getSharedPreferences("com.kata.skunkworks", Context.MODE_PRIVATE)
    private val gson = GsonBuilder().setPrettyPrinting().create()

    fun findAllSkunkWorks(): MutableList<SkunkWork> {
        val skunkWorksString: String = sharedPrefs.getString("skunkworksList", "") ?: ""
        return if(skunkWorksString != "") {
            gson.fromJson(skunkWorksString, object : TypeToken<MutableList<SkunkWork>>() {}.type)
        } else {
            defaultSkunkWorks
        }
    }

    fun addSkunkWork(skunkWork: SkunkWork) {
        val skunkWorks: MutableList<SkunkWork> = findAllSkunkWorks()
        skunkWorks.add(skunkWork)
        putSkunkWorks(skunkWorks)
    }

    fun deleteSkunkWork(id: Int) {
        val skunkWorks: MutableList<SkunkWork> = findAllSkunkWorks()
        skunkWorks.removeAt(id)
        putSkunkWorks(skunkWorks)
    }

    private fun putSkunkWorks(skunkWorks: List<SkunkWork>) {
        val editor: SharedPreferences.Editor = sharedPrefs.edit()
        editor.remove("skunkworksList")
        editor.apply()

        editor.putString("skunkworksList", gson.toJson(skunkWorks))
        editor.apply()
    }
}
