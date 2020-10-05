package com.github.komidawi.pizzacostcalculator.screen.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.komidawi.pizzacostcalculator.data.PizzaRepository
import com.github.komidawi.pizzacostcalculator.data.PizzaRepositoryImpl
import com.github.komidawi.pizzacostcalculator.data.db.FakeDatabaseDao
import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntityFactory
import com.github.komidawi.pizzacostcalculator.helper.MainCoroutineRule
import com.github.komidawi.pizzacostcalculator.screen.factory.ViewModelFactory
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
        val databaseDao = FakeDatabaseDao()
        pizzaRepository = PizzaRepositoryImpl(databaseDao)
        viewModel = ViewModelFactory(pizzaRepository).create(PizzaListFragmentViewModel::class.java)
    }

    @Test
    fun onRemove_removesPizzaFromDatabase() = runBlockingTest {
        // given
        val pizzaEntity = PizzaEntityFactory.create("RemovePizzaTest", 1, "1")
        pizzaRepository.insert(pizzaEntity)

        // when
        viewModel.onRemove(pizzaEntity)

        // then
        Thread.sleep(100) // TODO: fix
        assertNull(pizzaRepository.getById(pizzaEntity.id))
    }
}