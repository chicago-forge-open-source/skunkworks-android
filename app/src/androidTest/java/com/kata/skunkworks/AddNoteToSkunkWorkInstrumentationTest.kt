package com.kata.skunkworks

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class AddNoteToSkunkWorkInstrumentationTest{
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @get:Rule
    val activityRule = IntentsTestRule(SkunkWorkActivity::class.java)

    @Before
    fun setUp() {

    }

    @After
    fun cleanUp() {

    }

    @Test
    fun showsASkunkWorkWithAddNotesField() {
//        val skunkWorkList = onView(withId(R.id.skunk_works_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()))

    }

    fun clickOnViewChild(viewId: Int) = object : ViewAction {
        override fun getConstraints() = null

        override fun getDescription() = "Click on a child view with specified id."

        override fun perform(uiController: UiController, view: View) = click().perform(uiController, view.findViewById<View>(viewId))
    }
}