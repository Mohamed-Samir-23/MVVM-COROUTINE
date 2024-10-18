// ProductsRemoteDataSourceImpl.kt
package com.example.productskotlin.network

import com.example.mycart_mvvm.model.Product
import com.example.mykotlinapp1.network.DummyService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class ProductsRemoteDataSourceImpl(private val apiService: DummyService) : ProductsRemoteDataSource {

override suspend fun getProductsOverNetwork(): Flow<List<Product>> = flow {
    val response = apiService.getProducts()

    if (response.isSuccessful) {
        emit(response.body()?.products ?: listOf())
    } else {
        emit(listOf())
    }
}


    companion object {
        @Volatile
        private var INSTANCE: ProductsRemoteDataSourceImpl? = null

        fun getInstance(apiService: DummyService): ProductsRemoteDataSourceImpl {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ProductsRemoteDataSourceImpl(apiService).also { INSTANCE = it }
            }
        }
    }
}
