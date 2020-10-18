package com.github.komidawi.pizzacostcalculator.network

import com.github.komidawi.pizzacostcalculator.config.Env
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


private val interceptor = HttpLoggingInterceptor()
    .setLevel(HttpLoggingInterceptor.Level.BASIC)

private val httpClient = OkHttpClient.Builder()
    .addInterceptor(interceptor)

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(Env.BASE_URL)
    .client(httpClient.build())
    .build()

interface RestApiService {

    @GET(".")
    suspend fun getAllPizzas(): List<PizzaDto>

    @GET("uuid/{uuid}")
    suspend fun getPizzaByUuid(@Path("uuid") uuid: String)

    @POST(".")
    suspend fun insertPizza(@Body pizza: PizzaDto)

}

object RestApi {
    val retrofitService: RestApiService by lazy {
        retrofit.create(RestApiService::class.java)
    }
}