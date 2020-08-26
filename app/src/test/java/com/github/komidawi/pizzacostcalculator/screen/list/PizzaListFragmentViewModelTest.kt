package com.github.komidawi.pizzacostcalculator.screen.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.komidawi.pizzacostcalculator.data.db.FakeDatabaseDao
import com.github.komidawi.pizzacostcalculator.data.db.PizzaDatabaseDao
import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity
import com.github.komidawi.pizzacostcalculator.helper.MainCoroutineRule
import com.github.komidawi.pizzacostcalculator.screen.factory.ViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
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

    private lateinit var databaseDao: PizzaDatabaseDao

    @Before
    fun initialize() {
        databaseDao = FakeDatabaseDao()
        viewModel = ViewModelFactory(databaseDao).create(PizzaListFragmentViewModel::class.java)
    }

    @Test
    fun onRemove_removesPizzaFromDatabase() = runBlockingTest {
        // given
        val pizzaEntity = PizzaEntity("RemovePizzaTest", 1, "1")
        databaseDao.insert(pizzaEntity)

        // when
        viewModel.onRemove(pizzaEntity)

        // then
        Thread.sleep(5) // TODO: fix concurrency issue
        assertNull(databaseDao.getById(pizzaEntity.id))
    }
}