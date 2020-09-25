package com.github.komidawi.pizzacostcalculator.network

import com.github.komidawi.pizzacostcalculator.config.Env
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(Env.BASE_URL)
    .build()

interface RestApiService {
    @GET(".")
    fun getAllPizzas(): Call<String>
}

object RestApi {
    val retrofitService: RestApiService by lazy {
        retrofit.create(RestApiService::class.java)
    }
}