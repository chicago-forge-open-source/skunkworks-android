package com.kata.skunkworks

//import androidx.test.espresso.intent.rule.IntentsTestRule
//import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
//import org.junit.Rule
//import org.junit.runner.RunWith
//
//@RunWith(AndroidJUnit4ClassRunner::class)
//class RemoveSkunkWorkInstrumentedTest {
//
//    @get:Rule
//    val mainActivity: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)

/*    @Test
    fun removesASkunkwork() {
        val list: List<SkunkWork> = listOf(SkunkWork("A"))
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val prefs: SharedPreferences = context.getSharedPreferences("com.kata.skunkworks", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()

        editor.putString("skunkworksList", list.map(SkunkWork::title).joinToString(","))
        editor.commit()

        onView(withId(R.id.skunk_works_list)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.skunk_works_list)).check(matches(hasChildCount(list.size )))
    }*/
//}