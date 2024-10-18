package com.example.mycart_mvvm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mycart_mvvm.allproducts.view.AllProductsActivity
import com.example.mycart_mvvm.favproducts.view.FavProductsActivity

class MainActivity : AppCompatActivity() {
    private lateinit var btnAllProduct: Button
    private lateinit var btnFavProduct: Button
    private lateinit var btnExit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btnAllProduct = findViewById(R.id.btnAllProducts)
        btnFavProduct = findViewById(R.id.btnFavProducts)
        btnExit = findViewById(R.id.btnExit)
        btnAllProduct.setOnClickListener {
            val intent = Intent(this, AllProductsActivity::class.java)
            startActivity(intent)
        }
        btnFavProduct.setOnClickListener {
            val intent = Intent(this, FavProductsActivity::class.java)
            startActivity(intent)
        }

        btnExit.setOnClickListener {
            finish()
        }

    }
}