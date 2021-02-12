package com.github.komidawi.pizzacostcalculator.screen.add

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.komidawi.pizzacostcalculator.TestPizzaData.delta
import com.github.komidawi.pizzacostcalculator.TestPizzaData.testDeliveryCost
import com.github.komidawi.pizzacostcalculator.TestPizzaData.testName
import com.github.komidawi.pizzacostcalculator.TestPizzaData.testPizzeria
import com.github.komidawi.pizzacostcalculator.TestPizzaData.testPrice
import com.github.komidawi.pizzacostcalculator.TestPizzaData.testRatio
import com.github.komidawi.pizzacostcalculator.TestPizzaData.testSize
import com.github.komidawi.pizzacostcalculator.TestRepositoryFactory
import com.github.komidawi.pizzacostcalculator.data.repository.PizzaRepository
import com.github.komidawi.pizzacostcalculator.helper.MainCoroutineRule
import com.github.komidawi.pizzacostcalculator.helper.getOrAwaitValue
import com.github.komidawi.pizzacostcalculator.screen.factory.TestViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AddPizzaFragmentViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: AddPizzaFragmentViewModel

    private lateinit var pizzaRepository: PizzaRepository

    @Before
    fun initialize() {
        pizzaRepository = TestRepositoryFactory.create()
        viewModel =
            TestViewModelFactory(pizzaRepository).create(AddPizzaFragmentViewModel::class.java)
    }

    @Test
    fun createPizzaWithValidInput_returnsPizza() {
        // given
        setValidTestPizzaData()

        // when
        val createdPizza = viewModel.createPizza()!!

        // then
        assertEquals(testPizzeria, createdPizza.pizzeria)
        assertEquals(testName, createdPizza.name)
        assertEquals(testSize.toBigDecimal(), createdPizza.size)
        assertEquals(testPrice.toBigDecimal(), createdPizza.price)
        assertEquals(testDeliveryCost.toBigDecimal(), createdPizza.deliveryCost)
    }

    @Test
    fun calculateRatioWithValidValue_returnsRatio() {
        // given
        setPizzaData(size = testSize, price = testPrice)

        // when
        val ratio = viewModel.calculateRatio()

        // then
        assertEquals(testRatio, ratio.toDouble(), delta)
    }

    @Test
    fun createPizzaWithNullValues_returnsNull() {
        // when
        val createdPizza = viewModel.createPizza()

        // then
        assertNull(createdPizza)
    }

    @Test
    fun handleAddPizzaWithValidValue_insertsPizzaToDatabase() = mainCoroutineRule.runBlockingTest {
        // given
        setValidTestPizzaData()

        // when
        val createdPizza = viewModel.handleAddPizza()!!

        // then
        val receivedPizza = pizzaRepository.getByUuid(createdPizza.uuid)!!
        assertEquals(testPizzeria, createdPizza.pizzeria)
        assertEquals(testName, receivedPizza.name)
        assertEquals(testSize.toBigDecimal(), receivedPizza.size)
        assertEquals(testPrice.toBigDecimal(), receivedPizza.price)
        assertEquals(testDeliveryCost.toBigDecimal(), receivedPizza.deliveryCost)
    }

    @Test
    fun handleAddPizzaWithValidValue_triggersNavigationEvent() = mainCoroutineRule.runBlockingTest {
        // given
        setValidTestPizzaData()

        // when
        viewModel.handleAddPizza()

        // then
        assertTrue(viewModel.navigateToPizzaListFragment.getOrAwaitValue())
    }

    @Test
    fun handleAddPizzaWithNullValue_notInsertsPizzaToDatabase() =
        mainCoroutineRule.runBlockingTest {
            // when
            viewModel.handleAddPizza()

            // then
            assertEquals(0, pizzaRepository.getAll().getOrAwaitValue().size)
        }

    @Test
    fun handleAddPizzaWithNullValue_notTriggersNavigationEvent() =
        mainCoroutineRule.runBlockingTest {
            // when
            viewModel.handleAddPizza()

            // then
            assertFalse(viewModel.navigateToPizzaListFragment.getOrAwaitValue())
        }


    private fun setPizzaData(
        pizzeria: String? = null,
        name: String? = null,
        size: String? = null,
        price: String? = null,
        deliveryCost: String? = null
    ) {
        viewModel.pizzeria.value = pizzeria
        viewModel.name.value = name
        viewModel.size.value = size
        viewModel.price.value = price
        viewModel.deliveryCost.value = deliveryCost
    }

    private fun setValidTestPizzaData() {
        setPizzaData(testPizzeria, testName, testSize, testPrice, testDeliveryCost)
    }
}