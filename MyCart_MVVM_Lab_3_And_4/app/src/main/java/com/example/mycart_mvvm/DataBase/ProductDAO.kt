package com.example.mykotlinapp1.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mycart_mvvm.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDAO {

    @Query("SELECT * FROM products_table")
     fun getAllProducts(): Flow<List<Product>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllProduct(product: List<Product>): List<Long>

    @Delete
    suspend fun deleteProduct(product: Product): Int

    @Query("SELECT COUNT(*) FROM products_table")
    suspend fun getProductsCount(): Int

}