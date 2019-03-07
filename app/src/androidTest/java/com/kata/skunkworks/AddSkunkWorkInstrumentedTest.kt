package com.kata.skunkworks

import android.content.Context
import android.content.SharedPreferences
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class AddSkunkWorkInstrumentedTest {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val prefs: SharedPreferences = context.getSharedPreferences("com.kata.skunkworks", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = prefs.edit()
    private val list: List<SkunkWork> = listOf(SkunkWork("A"), SkunkWork("B"))

    @get:Rule
    val activityRule = IntentsTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        clearSharedPrefs()
        editor.putString("skunkworksList", list.map(SkunkWork::title).joinToString(","))
        editor.commit()
        onView(withId(R.id.show_add_skunk_work)).perform(click())
    }

    @After
    fun cleanUp() {
        clearSharedPrefs()
    }

    private fun clearSharedPrefs() {
        editor.clear()
        editor.commit()
    }

    @Test
    fun onShowAddSkunkWorkClickAddSkunkWorkActivityShown() {
        intended(hasComponent(AddSkunkWorkActivity::class.java.name))
        onView(withId(R.id.sw_title_edit_text)).check(matches(withHint("Title")))
        onView(withId(R.id.sw_save_btn)).check(matches(withText("Save")))
    }

    @Test
    fun testAddNewSkunkwork() {
        val newTitle = "Some New Idea"
        onView(withId(R.id.sw_title_edit_text)).perform(typeText(newTitle))
        onView(withId(R.id.sw_save_btn)).perform(click())
        onView(withId(R.id.skunk_works_list)).check(matches(hasChildCount(list.size + 1)))
        onView(withText(newTitle)).check(matches(isDisplayed()))
    }

    @Test
    fun testTrimBlankSpacesFromTitle() {
        val title = "Some New Idea"
        val newTitle = "     $title"
        onView(withId(R.id.sw_title_edit_text)).perform(typeText(newTitle))
        onView(withId(R.id.sw_save_btn)).perform(click())
        onView(withId(R.id.skunk_works_list)).check(matches(hasChildCount(list.size + 1)))
        onView(withText(title)).check(matches(isDisplayed()))
    }

    @Test
    fun testValidateForStingTitle() {
        onView(withId(R.id.sw_title_edit_text)).perform(typeText(""))
        onView(withId(R.id.sw_save_btn)).perform(click())
        intended(hasComponent(AddSkunkWorkActivity::class.java.name))
        val listSize = (prefs.getString("skunkworksList", "") ?: "").split(",").map(::SkunkWork).size
        assertEquals(list.size, listSize)
    }
}