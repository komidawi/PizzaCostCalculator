package com.github.komidawi.pizzacostcalculator.screen.add

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity
import com.github.komidawi.pizzacostcalculator.screen.factory.ViewModelFactory
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.math.BigDecimal

class AddPizzaFragmentViewModelTest {

    // This rule is mandatory to use ViewModel with LiveData
    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AddPizzaFragmentViewModel

    @Before
    fun initializeViewModel() {
        viewModel =
            ViewModelFactory(DummyDatabaseDao()).create(AddPizzaFragmentViewModel::class.java)
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

        // expected
        assertNull(createdPizza)
    }


    @Test
    fun handleAddPizzaWithNullValue_triggersToastEvent() {
        // given
        val observer = Observer<Boolean> { }
        viewModel.displayEmptyFieldsToast.observeForever(observer)

        try {
            // when
            viewModel.handleAddPizza()

            // then
            assertTrue(viewModel.displayEmptyFieldsToast.value!!)

        } finally {
            viewModel.displayEmptyFieldsToast.removeObserver(observer)
        }
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