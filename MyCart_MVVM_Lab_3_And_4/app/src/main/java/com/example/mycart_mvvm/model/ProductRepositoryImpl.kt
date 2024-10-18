package com.example.mycart_mvvm.model



import com.example.mycart_mvvm.DataBase.ProductsLocalDataSource
import com.example.productskotlin.network.ProductsRemoteDataSource
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl private constructor(
    private val remoteDataSource: ProductsRemoteDataSource,
    private val localDataSource: ProductsLocalDataSource
) :ProductsRepository{

    companion object {
        @Volatile
        private var INSTANCE: ProductRepositoryImpl? = null
        fun getInstance(remoteDataSource: ProductsRemoteDataSource, localDataSource: ProductsLocalDataSource): ProductRepositoryImpl {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ProductRepositoryImpl(remoteDataSource, localDataSource).also { INSTANCE = it }
            }

        }
    }


    override suspend fun getProducts(flag: Boolean): Flow<List<Product>> {
        if (flag) {
            return remoteDataSource.getProductsOverNetwork()
        } else {
            return localDataSource.getStoredProducts()
        }

    }

    override suspend fun insertProduct(product: Product) {
        localDataSource.insertProduct(product)
    }

    override suspend fun deleteProduct(product: Product) {
        localDataSource.deleteProduct(product)
    }

}
