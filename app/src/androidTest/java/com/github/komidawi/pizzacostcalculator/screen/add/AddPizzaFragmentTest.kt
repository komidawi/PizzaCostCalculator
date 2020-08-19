package com.github.komidawi.pizzacostcalculator.screen.add

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.github.komidawi.pizzacostcalculator.R
import com.github.komidawi.pizzacostcalculator.ServiceLocator
import com.github.komidawi.pizzacostcalculator.data.db.FakeAndroidTestDatabaseDao
import com.github.komidawi.pizzacostcalculator.data.db.PizzaDatabaseDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*

@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
class AddPizzaFragmentTest {

    private lateinit var databaseDao: PizzaDatabaseDao

    @Before
    fun initializeDatabase() {
        databaseDao = FakeAndroidTestDatabaseDao()
        ServiceLocator.databaseDao = databaseDao
    }

    @After
    fun cleanupDatabase() = runBlockingTest {
        ServiceLocator.resetDatabase()
    }

    @Test
    fun insertValidPizzaDataAndClickAdd_triggersNavigation() {
        // given
        val scenario = launchFragmentInContainer<AddPizzaFragment>(Bundle(), R.style.AppTheme)
        val navController = mock(NavController::class.java)
        scenario.onFragment { Navigation.setViewNavController(it.view!!, navController) }

        // and
        onView(withId(R.id.pizza_name_input)).perform(typeText("PizzaName"))
        onView(withId(R.id.pizza_size_input)).perform(typeText("42"))
        onView(withId(R.id.pizza_price_input)).perform(typeText("24.99"))

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
}