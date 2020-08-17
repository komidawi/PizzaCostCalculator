package com.github.komidawi.pizzacostcalculator.screen.add

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.komidawi.pizzacostcalculator.MainCoroutineRule
import com.github.komidawi.pizzacostcalculator.data.db.PizzaDatabaseDao
import com.github.komidawi.pizzacostcalculator.getOrAwaitValue
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
        assertEquals(testSize, createdPizza.size)
        assertEquals(testPrice, createdPizza.price)
    }

    @Test
    fun createPizzaWithNullValue_returnsNull() {
        // when
        val createdPizza = viewModel.createPizza()

        // then
        assertNull(createdPizza)
    }


    @Test
    fun handleAddPizzaWithNullValue_triggersEmptyFieldsToastEvent() {
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
        assertEquals(testSize, receivedPizza.size)
        assertEquals(testPrice, receivedPizza.price)
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

    companion object {
        private const val testName = "TestPizzaName"
        private const val testSize = "42"
        private const val testPrice = "30"

        fun setValidTestPizzaData(viewModel: AddPizzaFragmentViewModel) {
            viewModel.apply {
                name.value = testName
                size.value = testSize
                price.value = testPrice
            }
        }
    }
}