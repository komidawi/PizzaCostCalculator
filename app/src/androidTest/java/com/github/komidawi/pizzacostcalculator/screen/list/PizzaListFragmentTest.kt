package com.github.komidawi.pizzacostcalculator.screen.list

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.github.komidawi.pizzacostcalculator.R
import com.github.komidawi.pizzacostcalculator.ServiceLocator
import com.github.komidawi.pizzacostcalculator.data.db.FakeAndroidTestDatabaseDao
import com.github.komidawi.pizzacostcalculator.data.db.PizzaDatabaseDao
import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.math.BigDecimal

@ExperimentalCoroutinesApi
@MediumTest
@RunWith(AndroidJUnit4::class)
class PizzaListFragmentTest {

    private lateinit var databaseDao: PizzaDatabaseDao

    @Before
    fun initializeDatabase() {
        databaseDao = FakeAndroidTestDatabaseDao()
        ServiceLocator.databaseDao = databaseDao
    }

    @After
    fun cleanupDatabase() = runBlockingTest {
        ServiceLocator.resetDatabase()
    }

    @Test
    fun insertPizza_fragmentLaunches() = runBlockingTest {
        // given
        val pizza = PizzaEntity("Name", BigDecimal("42"), BigDecimal("24.99"))
        databaseDao.insert(pizza)

        // when
        launchFragmentInContainer<PizzaListFragment>(themeResId = R.style.AppTheme)
    }
}