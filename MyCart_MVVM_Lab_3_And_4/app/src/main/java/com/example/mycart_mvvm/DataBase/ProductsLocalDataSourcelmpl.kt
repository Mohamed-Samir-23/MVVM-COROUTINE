package com.example.mycart_mvvm.DataBase

import android.content.Context
import com.example.mycart_mvvm.model.Product
import com.example.mykotlinapp1.DataBase.AppDataBase
import com.example.mykotlinapp1.DataBase.ProductDAO
import kotlinx.coroutines.flow.Flow

class ProductsLocalDataSourceImpl(private val productDAO: ProductDAO) : ProductsLocalDataSource {

    override suspend fun getStoredProducts(): Flow<List<Product>> {
        return productDAO.getAllProducts()
    }

    override suspend fun insertProduct(product: Product) {
        productDAO.insertProduct(product)
    }

    override suspend fun deleteProduct(product: Product) {
        productDAO.deleteProduct(product)
    }

    override suspend fun updateProduct(product: Product) {
        productDAO.insertProduct(product)
    }

    companion object {
        @Volatile
        private var INSTANCE: ProductsLocalDataSourceImpl? = null

        fun getInstance(context: Context): ProductsLocalDataSourceImpl {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ProductsLocalDataSourceImpl(
                    AppDataBase.getInstance(context).getProductDAO()
                ).also { INSTANCE = it }
            }
        }
    }
}
