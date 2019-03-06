package com.kata.skunkworks

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasChildCount
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class SkunkWorkListInstrumentedTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun allSkunkWorksShouldAppearInList() {
        val list: List<SkunkWork> = listOf(SkunkWork("A"), SkunkWork("B"))

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val prefs: SharedPreferences = context.getSharedPreferences("com.kata.skunkworks", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()

        editor.remove("skunkworksList")
        editor.putString("skunkworksList", list.map(SkunkWork::title).joinToString(","))
        editor.commit()

        activityRule.launchActivity(Intent())
        onView(withId(R.id.skunk_works_list)).check(matches(hasChildCount(list.size)))
    }
}