package com.kata.skunkworks

import android.content.Context

class SkunkWorkRepository(val context: Context) {
    private val defaultString = """
        Can Beam,Mini Drone Forge Tour Guide,Smart Light That Goes Red When Build Fails,NFC Chip That Gives Wifi Access,
        NFC Ventra Clothing,Train Set,Nap Pods,DX War Room,Greeting Robot That Recognizes You Based On Key Card,
        Cool Light For When Creative Collision is Ready,Interactive Room Reservation System,Custom Magnet All The Things,
        Amiibo Features Around Features Around The Office,Card Wall With NFC Chips
    """.trimIndent().replace("\n", "")

    fun findAllSkunkWorks(): List<SkunkWork> {
        val sharedPrefs = context.getSharedPreferences("com.kata.skunkworks", Context.MODE_PRIVATE)
        val skunkWorksString: String = sharedPrefs.getString("skunkworksList", defaultString)
                ?: defaultString
        return skunkWorksString.split(",").map(::SkunkWork)
    }

}
