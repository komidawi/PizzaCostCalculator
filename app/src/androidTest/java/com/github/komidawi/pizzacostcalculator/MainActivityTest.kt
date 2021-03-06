package com.github.komidawi.pizzacostcalculator

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.github.komidawi.pizzacostcalculator.TestPizzaData.testName
import com.github.komidawi.pizzacostcalculator.TestPizzaData.testPizzeria
import com.github.komidawi.pizzacostcalculator.data.repository.PizzaRepository
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    private lateinit var pizzaRepository: PizzaRepository

    @Before
    fun setupPizzaRepository() {
        pizzaRepository =
            ServiceLocator.providePizzaRepository(ApplicationProvider.getApplicationContext())

        runBlocking {
            pizzaRepository.deleteAll()
        }
    }

    @After
    fun resetRepository() {
        ServiceLocator.resetRepository()
    }

    @Test
    fun addNewPizza_displaysPizzaInList() = runBlocking {
        // given
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // when
        onView(withId(R.id.add_pizza_fab)).perform(click())

        // and
        onView(withId(R.id.pizza_pizzeria_input)).perform(typeText(testPizzeria))
        onView(withId(R.id.pizza_name_input)).perform(typeText(testName))
        onView(withId(R.id.pizza_size_input)).perform(typeText("50"))
        onView(withId(R.id.pizza_price_input)).perform(typeText("34.99"))
        onView(withId(R.id.pizza_delivery_cost_input)).perform(typeText("3.50"))
        onView(withId(R.id.pizza_add_button)).perform(closeSoftKeyboard())
        onView(withId(R.id.pizza_add_button)).perform(click())

        // then
        onView(withText(testPizzeria)).check(matches(isDisplayed()))
        onView(withText(testName)).check(matches(isDisplayed()))

        // cleanup
        activityScenario.close()
    }

    @Test
    fun removePizzaButton_removesPizzaFromList() = runBlocking {
        // given
        val pizza = TestPizzaData.createTestPizza()
        pizzaRepository.insert(pizza)

        // and
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // when
        onView(withId(R.id.pizza_remove_button)).perform(click())

        // then
        onView(withId(R.id.pizza_name_display)).check(doesNotExist())
        assertNull(pizzaRepository.getByUuid(pizza.uuid))

        // cleanup
        activityScenario.close()
    }
}