package com.github.komidawi.pizzacostcalculator.data.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.komidawi.pizzacostcalculator.TestPizzaData
import com.github.komidawi.pizzacostcalculator.helper.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class RemoteDataSourceTest {

    private val dataSource: DataSource =
        RemoteDataSource(FakeRestApi.fakeRetrofitService, TestCoroutineDispatcher())

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
}