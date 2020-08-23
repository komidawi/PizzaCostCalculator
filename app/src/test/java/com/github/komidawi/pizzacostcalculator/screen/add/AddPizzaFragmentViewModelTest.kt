package com.github.komidawi.pizzacostcalculator.screen.add

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.komidawi.pizzacostcalculator.helper.MainCoroutineRule
import com.github.komidawi.pizzacostcalculator.TestPizzaData.testName
import com.github.komidawi.pizzacostcalculator.TestPizzaData.testPrice
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
        setValidTestPizzaData(viewModel)

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
    fun handleAddPizzaWithNullName_triggersEmptyFieldsToastEvent() {
        // given
        viewModel.apply {
            name.value = null
            size.value = testSize
            price.value = testPrice
        }

        // when
        viewModel.handleAddPizza()

        // then
        assertTrue(viewModel.displayEmptyFieldsToast.getOrAwaitValue())
    }

    @Test
    fun handleAddPizzaWithNullSize_triggersEmptyFieldsToastEvent() {
        // given
        viewModel.apply {
            name.value = testSize
            size.value = null
            price.value = testPrice
        }

        // when
        viewModel.handleAddPizza()

        // then
        assertTrue(viewModel.displayEmptyFieldsToast.getOrAwaitValue())
    }

    @Test
    fun handleAddPizzaWithNullPrice_triggersEmptyFieldsToastEvent() {
        // given
        viewModel.apply {
            name.value = testName
            size.value = testSize
            price.value = null
        }

        // when
        viewModel.handleAddPizza()

        // then
        assertTrue(viewModel.displayEmptyFieldsToast.getOrAwaitValue())
    }

    @Test
    fun handleAddPizzaWithEmptyName_triggersEmptyFieldsToastEvent() {
        // given
        viewModel.name.value = ""
        viewModel.size.value = testSize
        viewModel.price.value = testPrice

        // when
        viewModel.handleAddPizza()

        // then
        assertTrue(viewModel.displayEmptyFieldsToast.getOrAwaitValue())
    }

    @Test
    fun handleAddPizzaWithEmptySize_triggersEmptyFieldsToastEvent() {
        // given
        viewModel.name.value = testName
        viewModel.size.value = ""
        viewModel.price.value = testPrice

        // when
        viewModel.handleAddPizza()

        // then
        assertTrue(viewModel.displayEmptyFieldsToast.getOrAwaitValue())
    }

    @Test
    fun handleAddPizzaWithEmptyPrice_triggersEmptyFieldsToastEvent() {
        // given
        viewModel.name.value = testName
        viewModel.size.value = testSize
        viewModel.price.value = ""

        // when
        viewModel.handleAddPizza()

        // then
        assertTrue(viewModel.displayEmptyFieldsToast.getOrAwaitValue())
    }

    @Test
    fun handleAddPizzaWithValidValue_insertsPizzaToDatabase() = runBlockingTest {
        // given
        setValidTestPizzaData(viewModel)
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
    fun handleAddPizzaWithValidValue_triggersNavigationEvent() = runBlockingTest {
        // given
        setValidTestPizzaData(viewModel)

        // when
        viewModel.handleAddPizza()

        // then
        assertTrue(viewModel.navigateToPizzaListFragment.getOrAwaitValue())
    }

    @Test
    fun handleAddPizzaWithNullValue_notInsertsPizzaToDatabase() = runBlockingTest {
        // when
        viewModel.handleAddPizza()

        // then
        assertEquals(0, databaseDao.getAll().getOrAwaitValue().size)
    }


    private fun setValidTestPizzaData(viewModel: AddPizzaFragmentViewModel) {
        viewModel.apply {
            name.value = testName
            size.value = testSize
            price.value = testPrice
        }
    }
}