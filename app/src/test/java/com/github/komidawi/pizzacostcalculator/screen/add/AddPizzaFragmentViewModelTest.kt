package com.github.komidawi.pizzacostcalculator.screen.add

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity
import com.github.komidawi.pizzacostcalculator.screen.factory.ViewModelFactory
import org.junit.Assert.assertEquals
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
            ViewModelFactory(TestDatabaseDao()).create(AddPizzaFragmentViewModel::class.java)
    }

    @Test
    fun createPizza() {
        // given
        val testName = "TestPizzaName"
        val testSize = "42"
        val testValue = "30"
        val expectedPizza = PizzaEntity(0L, testName, BigDecimal(testSize), BigDecimal(testValue))

        // when
        viewModel.apply {
            name.value = testName
            size.value = testSize
            price.value = testValue
        }

        val createdPizza = viewModel.createPizza()

        // then
        assertEquals(expectedPizza, createdPizza)
    }
}