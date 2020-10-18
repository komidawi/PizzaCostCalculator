package com.github.komidawi.pizzacostcalculator.data.datasource

import com.github.komidawi.pizzacostcalculator.network.PizzaDto
import com.github.komidawi.pizzacostcalculator.network.RestApiService

object FakeRestApi {
    val fakeRetrofitService = FakeRestApiService()
}

class FakeRestApiService : RestApiService {

    private val data = mutableSetOf<PizzaDto>()

    override suspend fun getAllPizzas(): List<PizzaDto> = data.toList()

    override suspend fun getPizzaByUuid(uuid: String): PizzaDto? = data.find { it.uuid == uuid }

    override suspend fun insertPizza(pizza: PizzaDto) {
        data.add(pizza)
    }
}