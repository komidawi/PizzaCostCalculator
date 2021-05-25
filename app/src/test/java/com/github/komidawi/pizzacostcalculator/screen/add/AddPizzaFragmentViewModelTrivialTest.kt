package com.github.komidawi.pizzacostcalculator.screen.add

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.komidawi.pizzacostcalculator.TestPizzaData
import com.github.komidawi.pizzacostcalculator.TestRepositoryFactory
import com.github.komidawi.pizzacostcalculator.data.repository.PizzaRepository
import com.github.komidawi.pizzacostcalculator.helper.MainCoroutineRule
import com.github.komidawi.pizzacostcalculator.helper.getOrAwaitValue
import com.github.komidawi.pizzacostcalculator.screen.factory.TestViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.math.BigDecimal

@ExperimentalCoroutinesApi
class AddPizzaFragmentViewModelTrivialTest {

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
    fun calculateRatioWhenNullSize_returnsZero() {
        // given
        setPizzaData(price = TestPizzaData.testPrice)

        // when
        val ratio = viewModel.calculateRatio()

        // then
        Assert.assertEquals(BigDecimal.ZERO, ratio)
    }

    @Test
    fun calculateRatioWhenEmptySize_returnsZero() {
        // given
        setPizzaData(price = "")

        // when
        val ratio = viewModel.calculateRatio()

        // then
        Assert.assertEquals(BigDecimal.ZERO, ratio)
    }

    @Test
    fun calculateRatioWhenZeroSize_returnsZero() {
        // given
        setPizzaData(price = "0")

        // when
        val ratio = viewModel.calculateRatio()

        // then
        Assert.assertEquals(BigDecimal.ZERO, ratio)
    }

    @Test
    fun handleAddPizzaWithNullName_triggersEmptyFieldsToastEvent() {
        // given
        setPizzaData(null, TestPizzaData.testSize, TestPizzaData.testPrice)

        // when
        viewModel.handleAddPizza()

        // then
        Assert.assertTrue(viewModel.displayEmptyFieldsToast.getOrAwaitValue())
    }

    @Test
    fun handleAddPizzaWithNullSize_triggersEmptyFieldsToastEvent() {
        // given
        setPizzaData(TestPizzaData.testSize, null, TestPizzaData.testPrice)

        // when
        viewModel.handleAddPizza()

        // then
        Assert.assertTrue(viewModel.displayEmptyFieldsToast.getOrAwaitValue())
    }

    @Test
    fun handleAddPizzaWithNullPrice_triggersEmptyFieldsToastEvent() {
        // given
        setPizzaData(TestPizzaData.testName, TestPizzaData.testSize, null)

        // when
        viewModel.handleAddPizza()

        // then
        Assert.assertTrue(viewModel.displayEmptyFieldsToast.getOrAwaitValue())
    }

    @Test
    fun handleAddPizzaWithEmptyName_triggersEmptyFieldsToastEvent() {
        // given
        setPizzaData("", TestPizzaData.testSize, TestPizzaData.testPrice)

        // when
        viewModel.handleAddPizza()

        // then
        Assert.assertTrue(viewModel.displayEmptyFieldsToast.getOrAwaitValue())
    }

    @Test
    fun handleAddPizzaWithEmptySize_triggersEmptyFieldsToastEvent() {
        // given
        setPizzaData(TestPizzaData.testName, "", TestPizzaData.testPrice)

        // when
        viewModel.handleAddPizza()

        // then
        Assert.assertTrue(viewModel.displayEmptyFieldsToast.getOrAwaitValue())
    }

    @Test
    fun handleAddPizzaWithEmptyPrice_triggersEmptyFieldsToastEvent() {
        // given
        setPizzaData(TestPizzaData.testName, TestPizzaData.testSize, "")

        // when
        viewModel.handleAddPizza()

        // then
        Assert.assertTrue(viewModel.displayEmptyFieldsToast.getOrAwaitValue())
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
}