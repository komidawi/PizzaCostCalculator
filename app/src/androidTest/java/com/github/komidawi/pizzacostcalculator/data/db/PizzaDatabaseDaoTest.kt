package com.github.komidawi.pizzacostcalculator.data.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.github.komidawi.pizzacostcalculator.domain.PizzaFactory
import com.github.komidawi.pizzacostcalculator.domain.asDatabaseModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class PizzaDatabaseDaoTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: PizzaDatabase

    @Before
    fun initializeDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            PizzaDatabase::class.java
        ).build()
    }

    @After
    fun closeDatabase() = database.close()

    @Test
    fun insertPizzaAndGetByUuid() = runBlockingTest {
        // given
        val pizza = PizzaFactory.create("Name", "42", "24.99")

        // when
        database.pizzaDatabaseDao.insert(pizza.asDatabaseModel())

        // then
        val received = database.pizzaDatabaseDao.getByUuid(pizza.uuid.toString())!!
        assertEquals(pizza.uuid.toString(), received.uuid)
        assertEquals(pizza.name, received.name)
        assertEquals(pizza.size.toString(), received.size)
        assertEquals(pizza.price.toString(), received.price)
    }
}