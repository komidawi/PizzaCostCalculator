package com.github.komidawi.pizzacostcalculator.screen.add

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.komidawi.pizzacostcalculator.data.db.PizzaDatabaseDao
import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity
import com.github.komidawi.pizzacostcalculator.getOrAwaitValue
import com.github.komidawi.pizzacostcalculator.screen.factory.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
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

    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: AddPizzaFragmentViewModel

    private lateinit var databaseDao: PizzaDatabaseDao

    @Before
    fun initialize() {
        Dispatchers.setMain(testDispatcher)
        databaseDao = FakeDatabaseDao()
        viewModel = ViewModelFactory(databaseDao).create(AddPizzaFragmentViewModel::class.java)
    }

    @After
    fun tearDownDispatcher() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun createPizzaWithValidInput_returnsPizza() {
        // given
        val expectedPizza = PizzaEntity(0L, testName, BigDecimal(testSize), BigDecimal(testValue))

        // when
        setValidTestPizzaData(viewModel)
        val createdPizza = viewModel.createPizza()

        // then
        assertEquals(expectedPizza, createdPizza)
    }

    @Test
    fun createPizzaWithNullValue_returnsNull() {
        // when
        val createdPizza = viewModel.createPizza()

        // then
        assertNull(createdPizza)
    }


    @Test
    fun handleAddPizzaWithNullValue_triggersToastEvent() {
        // when
        viewModel.handleAddPizza()

        // then
        assertTrue(viewModel.displayEmptyFieldsToast.getOrAwaitValue())
    }

    @Test
    fun handleAddPizzaWithValidValue_insertsPizzaToDatabase() = runBlockingTest {
        setValidTestPizzaData(viewModel)
        val createdPizza = viewModel.createPizza()!!
        viewModel.handleAddPizza()

        // then
        val receivedPizza = databaseDao.getById(createdPizza.id)
        val all = databaseDao.getAll()

        // TODO does not work
        assertEquals(1, all.value?.size)
        assertEquals(createdPizza, receivedPizza)
    }

    companion object {
        private const val testName = "TestPizzaName"
        private const val testSize = "42"
        private const val testValue = "30"

        fun setValidTestPizzaData(viewModel: AddPizzaFragmentViewModel) {
            viewModel.apply {
                name.value = testName
                size.value = testSize
                price.value = testValue
            }
        }
    }
}