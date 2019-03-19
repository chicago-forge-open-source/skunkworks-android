package com.kata.skunkworks

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
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
    fun showsASkunkWorkWithAddNotesField() {
        onView(withId(R.id.skunk_works_list))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
//        onData(allOf(`is`(instanceOf(String::class.java)))).atPosition(2).perform(click())
        onView(withId(R.id.sw_title)).check(matches(withText("Can Beam")))
    }
}