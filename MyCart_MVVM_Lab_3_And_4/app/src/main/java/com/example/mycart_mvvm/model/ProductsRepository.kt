package com.example.mycart_mvvm.model

import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProducts(flag: Boolean): Flow<List<Product>>

    suspend fun insertProduct(product: Product)

    suspend fun deleteProduct(product: Product)
}