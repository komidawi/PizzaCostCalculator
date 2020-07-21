package com.github.komidawi.pizzacostcalculator.database

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class PizzaDatabaseTest {

    private lateinit var pizzaDatabaseDao: PizzaDatabaseDao
    private lateinit var database: PizzaDatabase

    @Before
    fun createDatabase() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, PizzaDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        pizzaDatabaseDao = database.pizzaDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetPizza() {
        val name = "test_name"
        val id = 1L
        val pizza = PizzaEntity(id, name, 123f, 456f)

        pizzaDatabaseDao.insert(pizza)

        val returnedPizza = pizzaDatabaseDao.getById(id)
        assertEquals(name, returnedPizza?.name)
    }
}