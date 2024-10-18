package com.example.mycart_mvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "products_table")
data class Product(@PrimaryKey(autoGenerate = true) var id: Int=0, var title: String, var description: String,var price:Double,var rating :Double,var brand:String ,var thumbnail: String):
    Serializable