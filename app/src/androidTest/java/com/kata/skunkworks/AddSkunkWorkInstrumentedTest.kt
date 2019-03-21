package com.kata.skunkworks

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
    private val prefs = createSharedPrefs(context)
    private val editor = prefs.edit()
    private val list: List<SkunkWork> = listOf(SkunkWork("A"), SkunkWork("B"))

    @get:Rule
    val activityRule = IntentsTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        clearSharedPrefs(editor)
        putListOfSkunkWorksSharedPrefs(editor, list)
        onView(withId(R.id.show_add_skunk_work)).perform(click())
    }

    @After
    fun cleanUp() {
        clearSharedPrefs(editor)
    }

    @Test
    fun whenClickingAddSkunkWorkShowsAddSkunkWorkActivity() {
        intended(hasComponent(SkunkWorkDetailActivity::class.java.name))
        onView(withId(R.id.sw_title_edit_text)).check(matches(withHint("Title")))
        onView(withId(R.id.sw_save_btn)).check(matches(withText("Save")))
    }

    @Test
    fun addsNewSkunkwork() {
        val newTitle = "Some New Idea"
        onView(withId(R.id.sw_title_edit_text)).perform(typeText(newTitle))
        onView(withId(R.id.sw_save_btn)).perform(click())

        onView(withId(R.id.skunk_works_list)).check(matches(hasChildCount(list.size + 1)))
        onView(withText(newTitle)).check(matches(isDisplayed()))
    }

    @Test
    fun trimsBlankSpacesFromTitle() {
        val title = "Some New Idea"
        val newTitle = "     $title"
        onView(withId(R.id.sw_title_edit_text)).perform(typeText(newTitle))
        onView(withId(R.id.sw_save_btn)).perform(click())

        onView(withId(R.id.skunk_works_list)).check(matches(hasChildCount(list.size + 1)))
        onView(withText(title)).check(matches(isDisplayed()))
    }

    @Test
    fun validatesForStingTitle() {
        onView(withId(R.id.sw_title_edit_text)).perform(typeText(""))
        onView(withId(R.id.sw_save_btn)).perform(click())
        intended(hasComponent(SkunkWorkDetailActivity::class.java.name))
        val listSize = getSkunkWorksFromSharedPrefs(prefs).size
        assertEquals(list.size, listSize)
    }
}