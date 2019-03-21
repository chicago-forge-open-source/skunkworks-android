package com.kata.skunkworks

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
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
class SkunkWorkListInstrumentedTest {
    private val list: List<SkunkWork> = listOf(SkunkWork("A"), SkunkWork("B"))
    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val prefs = createSharedPrefs(context)
    private val editor = prefs.edit()

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Before
    fun setUp() {
        clearSharedPrefs(editor)
        putListOfSkunkWorksSharedPrefs(editor, list)
        activityRule.launchActivity(Intent())
    }

    @After
    fun cleanUp() {
        clearSharedPrefs(editor)
    }

    @Test
    fun allSkunkWorksShouldAppearInList() {
        onView(withId(R.id.skunk_works_list)).check(matches(hasChildCount(list.size)))
    }

    @Test
    fun allSkunkWorksShouldShowTitles() {
        onView(withText("A")).check(matches(isDisplayed()))
        onView(withText("B")).check(matches(isDisplayed()))
    }
}