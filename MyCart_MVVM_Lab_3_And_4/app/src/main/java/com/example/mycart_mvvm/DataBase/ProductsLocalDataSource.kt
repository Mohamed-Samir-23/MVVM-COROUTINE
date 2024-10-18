package com.example.mycart_mvvm.DataBase

import com.example.mycart_mvvm.model.Product
import kotlinx.coroutines.flow.Flow


interface ProductsLocalDataSource {
    suspend fun getStoredProducts(): Flow<List<Product>>

    suspend fun insertProduct(product: Product)

    suspend fun deleteProduct(product: Product)

    suspend fun updateProduct(product: Product)
}