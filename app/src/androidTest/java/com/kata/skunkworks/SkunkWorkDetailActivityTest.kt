package com.kata.skunkworks

import android.content.Intent
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
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class SkunkWorkDetailActivityTest {

    private val skunkWork = SkunkWork(
        title = "Can Beam",
        note = "Hello my baby, hello my darling, hello my rag time gal."
    )

    @get:Rule
    val activityRule = ActivityTestRule(SkunkWorkDetailActivity::class.java)

    @Before
    fun setUp() {
        val intent = Intent()
        intent.putExtra("title", skunkWork.title)
        intent.putExtra("note", skunkWork.note)

        activityRule.launchActivity(intent)
    }

    @After
    fun cleanUp() {
    }

    @Test
    fun whenClickingSkunkWorkTitleIsPopulatedInTextField() {
        onView(withId(R.id.sw_title_edit_text)).check(matches(withText(skunkWork.title)))
    }

    @Test
    fun whenNoNoteSkunkWorkHasEmptyNoteField() {
        onView(withId(R.id.sw_note_edit_text)).check(matches(withText(skunkWork.note)))
        onView(withId(R.id.sw_note_edit_text)).check(matches(withHint("Note")))
    }

}