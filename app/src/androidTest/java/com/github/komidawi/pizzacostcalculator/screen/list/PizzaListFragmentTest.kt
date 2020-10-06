package com.github.komidawi.pizzacostcalculator.screen.list

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.github.komidawi.pizzacostcalculator.R
import com.github.komidawi.pizzacostcalculator.ServiceLocator
import com.github.komidawi.pizzacostcalculator.TestPizzaData
import com.github.komidawi.pizzacostcalculator.TestRepositoryFactory
import com.github.komidawi.pizzacostcalculator.data.PizzaRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
@MediumTest
@RunWith(AndroidJUnit4::class)
class PizzaListFragmentTest {

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
    fun addPizzaFabClick_triggersNavigation() = runBlockingTest {
        // given
        val scenario = launchFragmentInContainer<PizzaListFragment>(themeResId = R.style.AppTheme)
        val navController = mock(NavController::class.java)
        scenario.onFragment { Navigation.setViewNavController(it.view!!, navController) }

        // when
        onView(withId(R.id.add_pizza_fab)).perform(click())

        // then
        verify(navController)
            .navigate(PizzaListFragmentDirections.actionPizzaListFragmentToAddPizzaFragment())
    }

    @Test
    fun removePizzaButton_removesPizza() = runBlockingTest {
        // given
        val pizza = TestPizzaData.createTestPizza()
        pizzaRepository.insert(pizza)

        // and
        launchFragmentInContainer<PizzaListFragment>(themeResId = R.style.AppTheme)

        // when
        onView(withId(R.id.pizza_remove_button)).perform(click())

        // then
        assertNull(pizzaRepository.getById(pizza.id))
    }
}