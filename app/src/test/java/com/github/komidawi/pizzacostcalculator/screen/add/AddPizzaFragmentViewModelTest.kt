package com.github.komidawi.pizzacostcalculator.screen.add

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.komidawi.pizzacostcalculator.TestPizzaData.delta
import com.github.komidawi.pizzacostcalculator.helper.MainCoroutineRule
import com.github.komidawi.pizzacostcalculator.TestPizzaData.testName
import com.github.komidawi.pizzacostcalculator.TestPizzaData.testPrice
import com.github.komidawi.pizzacostcalculator.TestPizzaData.testRatio
import com.github.komidawi.pizzacostcalculator.TestPizzaData.testSize
import com.github.komidawi.pizzacostcalculator.data.db.FakeDatabaseDao
import com.github.komidawi.pizzacostcalculator.data.db.PizzaDatabaseDao
import com.github.komidawi.pizzacostcalculator.helper.getOrAwaitValue
import com.github.komidawi.pizzacostcalculator.screen.factory.ViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.math.BigDecimal

@ExperimentalCoroutinesApi
class AddPizzaFragmentViewModelTest {

    // This rule is mandatory to use ViewModel with LiveData
    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: AddPizzaFragmentViewModel

    private lateinit var databaseDao: PizzaDatabaseDao

    @Before
    fun initialize() {
        databaseDao = FakeDatabaseDao()
        viewModel = ViewModelFactory(databaseDao).create(AddPizzaFragmentViewModel::class.java)
    }

    @Test
    fun createPizzaWithValidInput_returnsPizza() {
        // given
        setValidTestPizzaData()

        // when
        val createdPizza = viewModel.createPizza()!!

        // then
        assertEquals(testName, createdPizza.name)
        assertEquals(testSize.toBigDecimal(), createdPizza.size)
        assertEquals(testPrice.toBigDecimal(), createdPizza.price)
    }

    @Test
    fun createPizzaWithNullValue_returnsNull() {
        // when
        val createdPizza = viewModel.createPizza()

        // then
        assertNull(createdPizza)
    }

    @Test
    fun handleAddPizzaWithValidValue_insertsPizzaToDatabase() = mainCoroutineRule.runBlockingTest {
        // given
        setValidTestPizzaData()
        val createdPizza = viewModel.createPizza()!!

        // when
        viewModel.handleAddPizza()

        // then
        val receivedPizza = databaseDao.getById(createdPizza.id)!!
        assertEquals(testName, receivedPizza.name)
        assertEquals(testSize.toBigDecimal(), receivedPizza.size)
        assertEquals(testPrice.toBigDecimal(), receivedPizza.price)
    }

    @Test
    fun handleAddPizzaWithValidValue_triggersNavigationEvent() = mainCoroutineRule.runBlockingTest {
        // given
        setValidTestPizzaData()

        // when
        viewModel.handleAddPizza()
        Thread.sleep(1) // TODO: fix it - currently no idea how to make it work

        // then
        assertTrue(viewModel.navigateToPizzaListFragment.getOrAwaitValue())
    }

    @Test
    fun handleAddPizzaWithNullValue_notInsertsPizzaToDatabase() =
        mainCoroutineRule.runBlockingTest {
            // when
            viewModel.handleAddPizza()

            // then
            assertEquals(0, databaseDao.getAll().getOrAwaitValue().size)
        }

    @Test
    fun handleAddPizzaWithNullValue_notTriggersNavigationEvent() =
        mainCoroutineRule.runBlockingTest {
            // when
            viewModel.handleAddPizza()

            // then
            assertFalse(viewModel.navigateToPizzaListFragment.getOrAwaitValue())
        }

    @Test
    fun calculateRatioWithValidValue_returnsRatio() {
        // given
        setPizzaData(size = testSize, price = testPrice)

        // when
        val ratio = viewModel.calculateRatio()

        // then
        assertEquals(testRatio, ratio.toFloat(), delta)
    }

    @Test
    fun calculateRatioWhenNullSize_returnsZero() {
        // given
        setPizzaData(price = testPrice)

        // when
        val ratio = viewModel.calculateRatio()

        // then
        assertEquals(BigDecimal.ZERO, ratio)
    }

    @Test
    fun calculateRatioWhenEmptySize_returnsZero() {
        // given
        setPizzaData(price = "")

        // when
        val ratio = viewModel.calculateRatio()

        // then
        assertEquals(BigDecimal.ZERO, ratio)
    }

    @Test
    fun calculateRatioWhenZeroSize_returnsZero() {
        // given
        setPizzaData(price = "0")

        // when
        val ratio = viewModel.calculateRatio()

        // then
        assertEquals(BigDecimal.ZERO, ratio)
    }

    @Test
    fun handleAddPizzaWithNullName_triggersEmptyFieldsToastEvent() {
        // given
        setPizzaData(null, testSize, testPrice)

        // when
        viewModel.handleAddPizza()

        // then
        assertTrue(viewModel.displayEmptyFieldsToast.getOrAwaitValue())
    }

    @Test
    fun handleAddPizzaWithNullSize_triggersEmptyFieldsToastEvent() {
        // given
        setPizzaData(testSize, null, testPrice)

        // when
        viewModel.handleAddPizza()

        // then
        assertTrue(viewModel.displayEmptyFieldsToast.getOrAwaitValue())
    }

    @Test
    fun handleAddPizzaWithNullPrice_triggersEmptyFieldsToastEvent() {
        // given
        setPizzaData(testName, testSize, null)

        // when
        viewModel.handleAddPizza()

        // then
        assertTrue(viewModel.displayEmptyFieldsToast.getOrAwaitValue())
    }

    @Test
    fun handleAddPizzaWithEmptyName_triggersEmptyFieldsToastEvent() {
        // given
        setPizzaData("", testSize, testPrice)

        // when
        viewModel.handleAddPizza()

        // then
        assertTrue(viewModel.displayEmptyFieldsToast.getOrAwaitValue())
    }

    @Test
    fun handleAddPizzaWithEmptySize_triggersEmptyFieldsToastEvent() {
        // given
        setPizzaData(testName, "", testPrice)

        // when
        viewModel.handleAddPizza()

        // then
        assertTrue(viewModel.displayEmptyFieldsToast.getOrAwaitValue())
    }

    @Test
    fun handleAddPizzaWithEmptyPrice_triggersEmptyFieldsToastEvent() {
        // given
        setPizzaData(testName, testSize, "")

        // when
        viewModel.handleAddPizza()

        // then
        assertTrue(viewModel.displayEmptyFieldsToast.getOrAwaitValue())
    }

    private fun setPizzaData(name: String? = null, size: String? = null, price: String? = null) {
        viewModel.name.value = name
        viewModel.size.value = size
        viewModel.price.value = price
    }

    private fun setValidTestPizzaData() {
        setPizzaData(testName, testSize, testPrice)
    }
}