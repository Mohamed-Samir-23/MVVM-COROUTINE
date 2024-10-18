package com.example.mykotlinapp1.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mycart_mvvm.model.Product

@Database(entities = arrayOf(Product::class), version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getProductDAO(): ProductDAO
    companion object{
        @Volatile
        private var INSTANCE: AppDataBase? =null

        fun getInstance (context : Context):AppDataBase
        {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "productsdb"
                )
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }

        }
    }
}
