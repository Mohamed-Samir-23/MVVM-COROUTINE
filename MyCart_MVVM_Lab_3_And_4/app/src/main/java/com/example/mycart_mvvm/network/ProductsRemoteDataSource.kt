package com.example.productskotlin.network

import com.example.mycart_mvvm.model.Product
import kotlinx.coroutines.flow.Flow


interface ProductsRemoteDataSource {
    suspend  fun getProductsOverNetwork(): Flow<List<Product>>
}