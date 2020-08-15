package com.github.komidawi.pizzacostcalculator.data.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.math.BigDecimal

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
        val pizza = PizzaEntity("Name", BigDecimal("42"), BigDecimal("24.99"))

        // when
        database.pizzaDatabaseDao.insert(pizza)

        // then
        val received = database.pizzaDatabaseDao.getByUuid(pizza.uuid)!!
        assertEquals(pizza.uuid, received.uuid)
        assertEquals(pizza.name, received.name)
        assertEquals(pizza.size, received.size)
        assertEquals(pizza.price, received.price)
    }
}