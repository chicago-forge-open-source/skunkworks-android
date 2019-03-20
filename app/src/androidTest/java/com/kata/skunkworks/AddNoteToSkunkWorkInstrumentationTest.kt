package com.kata.skunkworks

import android.content.Context
import android.content.SharedPreferences
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class AddNoteToSkunkWorkInstrumentationTest{

    @get:Rule
    val activityRule = IntentsTestRule(MainActivity::class.java)

    @Before
    fun setUp() {

    }

    @After
    fun cleanUp() {

    }



    @Test
    fun whenClickingASkunkWorkShowAddSkunkWorkActivityPassesTitle() {
        val title ="Can Beam"
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val prefs: SharedPreferences = context.getSharedPreferences("com.kata.skunkworks", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()

        editor.remove("skunkworksList")
        editor.commit()
        editor.putString("skunkworksList", title)
        editor.commit()

        onView(withId(R.id.skunk_works_list))
            .perform(RecyclerViewActions
            .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        intended(hasComponent(SkunkWorkDetailActivity::class.java.name))
        intended(hasExtra("title", title))
    }
}