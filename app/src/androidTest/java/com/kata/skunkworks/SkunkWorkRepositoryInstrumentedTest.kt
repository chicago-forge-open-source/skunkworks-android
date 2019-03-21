package com.kata.skunkworks

import android.content.Context
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Ignore
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
            SkunkWork("Can Beam"),
            SkunkWork("Mini Drone Forge Tour Guide"),
            SkunkWork("Smart Light That Goes Red When Build Fails"),
            SkunkWork("NFC Chip That Gives Wifi Access"),
            SkunkWork("NFC Ventra Clothing"),
            SkunkWork("Train Set"),
            SkunkWork("Nap Pods"),
            SkunkWork("DX War Room"),
            SkunkWork("Greeting Robot"),
            SkunkWork("That Recognizes You Based On Key Card"),
            SkunkWork("Cool Light For When Creative Collision is Ready"),
            SkunkWork("Interactive Room Reservation System"),
            SkunkWork("Amiibo Features Around Features Around The Office"),
            SkunkWork("Card Wall With NFC Chips")
        )

        assertEquals(list, repo.findAllSkunkWorks())
    }

    @Test
    fun addSkunkWorksAddsSkunkWorkToList() {
        val skunkWork = SkunkWork("New SkunkWork")
        repo.addSkunkWork(skunkWork)
        assertTrue(repo.findAllSkunkWorks().contains(skunkWork))
    }

    @Test
    fun findsAllSkunkWorksWithNotes() {
        val list: List<SkunkWork> = listOf(SkunkWork("A"), SkunkWork("B", "a note"))
        putListOfSkunkWorksSharedPrefs(editor, list)
        assertEquals(list, repo.findAllSkunkWorks())
    }
}