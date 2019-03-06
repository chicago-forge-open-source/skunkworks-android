package com.kata.skunkworks

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SkunkWorkRepositoryInstrumentedTest {

    @Test
    fun givenAListOfSkunkWorksExistFindAllSkunkWorksReturnsTheList() {
        val list: List<SkunkWork> = listOf(SkunkWork("A"), SkunkWork("B"))
        val context = InstrumentationRegistry.getContext()

        val prefs = context.getSharedPreferences("com.kata.skunkworks", Context.MODE_PRIVATE)
        val repo = SkunkWorkRepository(context)
        val editor = prefs.edit()

        editor.putString("skunkworksList", list.map(SkunkWork::title).joinToString(","))
        editor.apply()

        assertEquals(list, repo.findAllSkunkWorks())
    }
}