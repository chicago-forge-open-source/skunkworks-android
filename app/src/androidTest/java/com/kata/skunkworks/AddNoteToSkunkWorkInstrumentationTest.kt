package com.kata.skunkworks

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class AddNoteToSkunkWorkInstrumentationTest {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val prefs = createSharedPrefs(context)
    private val editor = prefs.edit()
    private val title = "Can Beam"

    @get:Rule
    val activityRule = IntentsTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        clearSharedPrefs(editor)
        putListOfSkunkWorksSharedPrefs(editor, listOf(SkunkWork(title)))

        onView(withId(R.id.skunk_works_list))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
            )
    }

    @After
    fun cleanUp() {
        clearSharedPrefs(editor)
    }

    @Test
    fun whenClickingASkunkWorkShowAddSkunkWorkActivityPassesTitle() {
        intended(hasComponent(SkunkWorkDetailActivity::class.java.name))
        intended(hasExtra("title", title))
    }

    @Test
    fun whenClickingSkunkWorkTitleIsPopulatedInTextField() {
        onView(withId(R.id.sw_title_edit_text)).check(matches(withText(title)))
    }

    @Test
    fun whenNoNoteSkunkWorkHasEmptyNoteField() {
        onView(withId(R.id.sw_note_edit_text)).check(matches(withText("")))
        onView(withId(R.id.sw_note_edit_text)).check(matches(withHint("Note")))
    }
}