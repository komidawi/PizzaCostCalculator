package com.github.komidawi.pizzacostcalculator

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.github.komidawi.pizzacostcalculator.data.db.PizzaDatabaseDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    private lateinit var pizzaDatabaseDao: PizzaDatabaseDao

    @Before
    fun setupDatabaseDao() {
        pizzaDatabaseDao =
            ServiceLocator.provideDatabaseDao(ApplicationProvider.getApplicationContext())
        runBlocking {
            pizzaDatabaseDao.deleteAll()
        }
    }

    @After
    fun resetDatabase() {
        ServiceLocator.resetDatabase()
    }

    @Test
    fun e2e_test() = runBlocking {
        // given
        val testPizzaName = "TestPizzaName"
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // when
        onView(withId(R.id.add_pizza_fab)).perform(click())

        // and
        onView(withId(R.id.pizza_name_input)).perform(typeText(testPizzaName))
        onView(withId(R.id.pizza_size_input)).perform(typeText("50"))
        onView(withId(R.id.pizza_price_input)).perform(typeText("34.99"))
        onView(withId(R.id.pizza_add_button)).perform(click())

        // then
        onView(withText(testPizzaName)).check(matches(isDisplayed()))

        // cleanup
        activityScenario.close()
    }
}