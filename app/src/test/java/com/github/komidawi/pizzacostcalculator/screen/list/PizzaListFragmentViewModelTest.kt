package com.github.komidawi.pizzacostcalculator.screen.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.komidawi.pizzacostcalculator.TestRepositoryFactory
import com.github.komidawi.pizzacostcalculator.data.repository.PizzaRepository
import com.github.komidawi.pizzacostcalculator.domain.PizzaFactory
import com.github.komidawi.pizzacostcalculator.helper.MainCoroutineRule
import com.github.komidawi.pizzacostcalculator.screen.factory.TestViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PizzaListFragmentViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: PizzaListFragmentViewModel

    private lateinit var pizzaRepository: PizzaRepository

    @Before
    fun initialize() {
        pizzaRepository = TestRepositoryFactory.create()
        viewModel =
            TestViewModelFactory(pizzaRepository).create(PizzaListFragmentViewModel::class.java)
    }

    @Test
    fun onRemove_removesPizzaFromDatabase() = mainCoroutineRule.runBlockingTest {
        // given
        val pizzaEntity = PizzaFactory.create("", "", 1, "1")
        pizzaRepository.insert(pizzaEntity)

        // when
        viewModel.onRemove(pizzaEntity)

        // then
        assertNull(pizzaRepository.getByUuid(pizzaEntity.uuid))
    }
}