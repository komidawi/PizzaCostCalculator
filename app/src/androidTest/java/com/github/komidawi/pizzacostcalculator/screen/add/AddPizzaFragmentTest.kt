package com.github.komidawi.pizzacostcalculator.screen.add

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.github.komidawi.pizzacostcalculator.R
import com.github.komidawi.pizzacostcalculator.ServiceLocator
import com.github.komidawi.pizzacostcalculator.TestPizzaData.testDeliveryCost
import com.github.komidawi.pizzacostcalculator.TestPizzaData.testName
import com.github.komidawi.pizzacostcalculator.TestPizzaData.testPizzeria
import com.github.komidawi.pizzacostcalculator.TestPizzaData.testPrice
import com.github.komidawi.pizzacostcalculator.TestPizzaData.testRatioWithDeliveryDisplayText
import com.github.komidawi.pizzacostcalculator.TestPizzaData.testSize
import com.github.komidawi.pizzacostcalculator.TestRepositoryFactory
import com.github.komidawi.pizzacostcalculator.data.repository.PizzaRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.StringContains.containsString
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*

@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
class AddPizzaFragmentTest {

    private lateinit var pizzaRepository: PizzaRepository

    @Before
    fun initializeRepository() {
        pizzaRepository = TestRepositoryFactory.create()
        ServiceLocator.repository = pizzaRepository
    }

    @After
    fun cleanupRepository() = runBlockingTest {
        ServiceLocator.resetRepository()
    }

    @Test
    fun insertValidPizzaDataAndClickAdd_triggersNavigation() {
        // given
        val scenario = launchFragmentInContainer<AddPizzaFragment>(Bundle(), R.style.AppTheme)
        val navController = mock(NavController::class.java)
        scenario.onFragment { Navigation.setViewNavController(it.view!!, navController) }

        // and
        provideTestPizzaData()
        closeSoftKeyboard()

        // when
        onView(withId(R.id.pizza_add_button)).perform(click())

        // then
        verify(navController)
            .navigate(AddPizzaFragmentDirections.actionAddPizzaFragmentToPizzaListFragment())
    }

    @Test
    fun insertNoPizzaDataAndClickAdd_notTriggersNavigation() {
        // given
        val scenario = launchFragmentInContainer<AddPizzaFragment>(Bundle(), R.style.AppTheme)
        val navController = mock(NavController::class.java)
        scenario.onFragment { Navigation.setViewNavController(it.view!!, navController) }

        // when
        onView(withId(R.id.pizza_add_button)).perform(click())

        // then
        verify(navController, never())
            .navigate(AddPizzaFragmentDirections.actionAddPizzaFragmentToPizzaListFragment())
    }

    @Test
    fun insertValidPizzaData_updatesRatioValue() {
        // given
        launchFragmentInContainer<AddPizzaFragment>(Bundle(), R.style.AppTheme)

        // when
        provideTestPizzaData()
        closeSoftKeyboard()

        // then
        onView(withId(R.id.list_pizza_ratio_display))
            .check(matches(withText(containsString(testRatioWithDeliveryDisplayText))))
    }

    private fun provideTestPizzaData() {
        onView(withId(R.id.pizza_pizzeria_input)).perform(typeText(testPizzeria))
        onView(withId(R.id.pizza_name_input)).perform(typeText(testName))
        onView(withId(R.id.pizza_size_input)).perform(typeText(testSize))
        onView(withId(R.id.pizza_price_input)).perform(typeText(testPrice))
        onView(withId(R.id.pizza_delivery_cost_input)).perform(typeText(testDeliveryCost))
    }
}