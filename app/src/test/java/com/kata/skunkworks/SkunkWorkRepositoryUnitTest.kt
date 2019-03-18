package com.kata.skunkworks

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SkunkWorkRepositoryUnitTest {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val prefs = context.getSharedPreferences("com.kata.skunkworks", Context.MODE_PRIVATE)
    val editor = prefs.edit()
    val repo = SkunkWorkRepository(context)

    @Test
    fun givenAListOfSkunkWorksExistFindAllSkunkWorksReturnsTheList() {
        val list: List<SkunkWork> = listOf(SkunkWork("A"), SkunkWork("B"))
        editor.putString("skunkworksList", list.map(SkunkWork::title).joinToString(","))
        editor.apply()
        assertEquals(list, repo.findAllSkunkWorks())
    }

    @Test
    fun givenNoSkunksWorksExistSkunkWorksReturnsDefaultList() {
        val defaultList: List<SkunkWork> = listOf(
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
        ).map(::SkunkWork)

        assertEquals(defaultList, repo.findAllSkunkWorks())
    }

    @Test
    fun addSkunkWorksAddsSkunkWorkToList() {
        val skunkWork = SkunkWork("New SkunkWork")
        repo.addSkunkWork(skunkWork)
        assertTrue(repo.findAllSkunkWorks().contains(skunkWork))
    }
}