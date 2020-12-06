package id.ac.ui.cs.learn_tktpl_1706039843

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CounterInstrumentedTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule<MainActivity>(
        MainActivity::class.java
    )

    @Test
    fun checkValueTextViewTest() {
        Espresso.onView(ViewMatchers.withId(R.id.value)).check(matches(isDisplayed()))
    }

    @Test
    fun checkButtonsTest() {
        Espresso.onView(ViewMatchers.withId(R.id.plusButton)).check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.minusButton)).check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.resetButton)).check(matches(isDisplayed()))
    }

    @Test
    fun clickPlusButtonTest() {
        Espresso.onView(ViewMatchers.withId(R.id.plusButton)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.value))
            .check(matches(ViewMatchers.withText("1")))
    }

    @Test
    fun clickMinusButtonTest() {
        Espresso.onView(ViewMatchers.withId(R.id.plusButton)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.plusButton)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.minusButton)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.value))
            .check(matches(ViewMatchers.withText("1")))
    }

    @Test
    fun clickResetButtonTest() {
        Espresso.onView(ViewMatchers.withId(R.id.plusButton)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.plusButton)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.resetButton)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.value))
            .check(matches(ViewMatchers.withText("0")))
    }
}