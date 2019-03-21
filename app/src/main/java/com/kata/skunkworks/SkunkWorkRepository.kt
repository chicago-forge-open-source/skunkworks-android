package com.kata.skunkworks

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class SkunkWorkRepository(appContext: Context) {
    private val defaultString = """
        {"title":"Can Beam"};
        {"title":"Mini Drone Forge Tour Guide"};
        {"title":"Smart Light That Goes Red When Build Fails"};
        {"title":"NFC Chip That Gives Wifi Access"};
        {"title":"NFC Ventra Clothing"};{"title":"Train Set"};
        {"title":"Nap Pods"};{"title":"DX War Room"};
        {"title":"Greeting Robot"};
        {"title":"That Recognizes You Based On Key Card"};
        {"title":"Cool Light For When Creative Collision is Ready"};
        {"title":"Interactive Room Reservation System"};
        {"title":"Amiibo Features Around Features Around The Office"};
        {"title":"Card Wall With NFC Chips"}
        """.trimIndent().replace("\n", "")

    private val sharedPrefs = appContext.getSharedPreferences("com.kata.skunkworks", Context.MODE_PRIVATE)

    fun findAllSkunkWorks(): MutableList<SkunkWork> {
        val gson = Gson()
        val skunkWorksString: String = sharedPrefs.getString("skunkworksList", defaultString) ?: defaultString

        return skunkWorksString.split(";").map{ gson.fromJson(it, SkunkWork::class.java) }.toMutableList()
    }

    fun addSkunkWork(skunkWork: SkunkWork) {
        val editor: SharedPreferences.Editor = sharedPrefs.edit()
        val skunkWorks: MutableList<SkunkWork> = findAllSkunkWorks().toMutableList()

        skunkWorks.add(skunkWork)
        editor.remove("skunkworksList")
        editor.apply()
        val gson = GsonBuilder().setPrettyPrinting().create()
        editor.putString("skunkworksList", gson.toJson(skunkWork))
        editor.apply()
    }

    fun deleteSkunkWork(id: Int) {
        val editor: SharedPreferences.Editor = sharedPrefs.edit()
        val skunkWorks: MutableList<SkunkWork> = findAllSkunkWorks().toMutableList()

        skunkWorks.removeAt(id)
        editor.remove("skunkworksList")
        editor.apply()

        editor.putString("skunkworksList", skunkWorks.map(SkunkWork::title).joinToString(";"))
        editor.apply()
    }
}
