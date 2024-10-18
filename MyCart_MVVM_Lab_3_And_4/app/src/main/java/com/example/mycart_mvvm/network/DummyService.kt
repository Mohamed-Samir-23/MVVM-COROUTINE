package com.example.mykotlinapp1.network

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface DummyService {
    @GET("product")
    suspend fun getProducts(): Response<Dummyjson>
}

