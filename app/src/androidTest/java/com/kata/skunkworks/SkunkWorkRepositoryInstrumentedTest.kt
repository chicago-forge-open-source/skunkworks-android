package com.kata.skunkworks

import android.content.Context
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class SkunkWorkRepositoryInstrumentedTest {
    val context: Context = InstrumentationRegistry.getInstrumentation().context
    val prefs = context.getSharedPreferences("com.kata.skunkworks", Context.MODE_PRIVATE)
    val editor = prefs.edit()
    val repo = SkunkWorkRepository(context)

    @Before
    fun setUp() {
        clearSharedPrefs(editor)
    }

    @After
    fun cleanUp() {
        clearSharedPrefs(editor)
    }

    @Test
    fun givenAListOfSkunkWorksExistFindAllSkunkWorksReturnsTheList() {
        val list: List<SkunkWork> = listOf(SkunkWork("A"), SkunkWork("B"))
        putListOfSkunkWorksSharedPrefs(editor, list)
        assertEquals(list, repo.findAllSkunkWorks())
    }

    @Test
    fun givenNoSkunksWorksExistSkunkWorksReturnsDefaultList() {
        val list: List<SkunkWork> = listOf(
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
        ).map { SkunkWork(it) }

        assertEquals(list, repo.findAllSkunkWorks())
    }

    @Test
    fun findsAllSkunkWorksWithNotes() {
        val list: List<SkunkWork> = listOf(SkunkWork("A"), SkunkWork("B", "a note"))
        putListOfSkunkWorksSharedPrefs(editor, list)
        assertEquals(list, repo.findAllSkunkWorks())
    }

    @Test
    fun addSkunkWorksAddsSkunkWorkToList() {
        val list: List<SkunkWork> = listOf(SkunkWork("A"), SkunkWork("B", "a note"))
        putListOfSkunkWorksSharedPrefs(editor, list)

        val skunkWork = SkunkWork("New SkunkWork")
        repo.addSkunkWork(skunkWork)

        val updatedList : List<SkunkWork> = repo.findAllSkunkWorks()
        assertEquals(list.size + 1, updatedList.size)
        assertTrue(updatedList.contains(skunkWork))
    }
}