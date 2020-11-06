package com.github.komidawi.pizzacostcalculator.data.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.komidawi.pizzacostcalculator.TestPizzaData
import com.github.komidawi.pizzacostcalculator.domain.asNetworkModel
import com.github.komidawi.pizzacostcalculator.helper.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class RemoteDataSourceTest {

    val restService = FakeRestApi.fakeRetrofitService

    private val dataSource: DataSource =
        RemoteDataSource(restService, TestCoroutineDispatcher())

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    @Test
    fun insertAndReceivePizza() = mainCoroutineRule.runBlockingTest {
        // given
        val pizza = TestPizzaData.createTestPizza()

        // when
        dataSource.insert(pizza)

        // and
        val receivedPizza = dataSource.getByUuid(pizza.uuid)

        // then
        assertEquals(pizza, receivedPizza)
    }

    @Test
    fun deleteByUuid() = mainCoroutineRule.runBlockingTest {
        // given
        val pizza = TestPizzaData.createTestPizza()
        restService.insertPizza(pizza.asNetworkModel())

        // when
        dataSource.deleteByUuid(pizza.uuid)

        // then
        val receivedPizza = dataSource.getByUuid(pizza.uuid)
        assertNull(receivedPizza)
    }
}