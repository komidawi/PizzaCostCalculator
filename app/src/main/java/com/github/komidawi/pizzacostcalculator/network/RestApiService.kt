package com.github.komidawi.pizzacostcalculator.network

import com.github.komidawi.pizzacostcalculator.config.Env
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(Env.BASE_URL)
    .build()

interface RestApiService {
    @GET(".")
    suspend fun getAllPizzas(): List<PizzaDto>
}

object RestApi {
    val retrofitService: RestApiService by lazy {
        retrofit.create(RestApiService::class.java)
    }
}