package com.example.mycart_mvvm.favproducts.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycart_mvvm.DataBase.ProductsLocalDataSourceImpl
import com.example.mycart_mvvm.R
import com.example.mycart_mvvm.allproducts.view.AllProductsAdapter
import com.example.mycart_mvvm.allproducts.viewmodel.AllProductsViewModel
import com.example.mycart_mvvm.allproducts.viewmodel.AllProductsViewModelFactory
import com.example.mycart_mvvm.favproducts.viewmodel.FavViewModel
import com.example.mycart_mvvm.favproducts.viewmodel.FavViewModelFactory
import com.example.mycart_mvvm.model.ProductRepositoryImpl
import com.example.mycart_mvvm.network.RetrofitHelper
import com.example.mykotlinapp1.DataBase.AppDataBase
import com.example.mykotlinapp1.network.DummyService
import com.example.productskotlin.network.NetworkConnectionStatus
import com.example.productskotlin.network.ProductsRemoteDataSourceImpl

class FavProductsActivity : AppCompatActivity() {
    private lateinit var recyclerAdapter: FavProductsAdapter
    private lateinit var networkStatus: NetworkConnectionStatus
    private lateinit var viewModel: FavViewModel
    lateinit var allFavProductsLayoutManager: LinearLayoutManager
    lateinit var recyclerView: RecyclerView
    lateinit var allFavProductFactory: FavViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fav_products)

        allFavProductFactory=FavViewModelFactory(
            ProductRepositoryImpl.getInstance(
                ProductsRemoteDataSourceImpl.getInstance(RetrofitHelper.getInstance().create(
                    DummyService::class.java)),
                ProductsLocalDataSourceImpl(AppDataBase.getInstance(this).getProductDAO())
            ))


        viewModel = ViewModelProvider(this, allFavProductFactory).get(FavViewModel::class.java)
        recyclerView= findViewById(R.id.recycViewFav)
        recyclerAdapter = FavProductsAdapter(this) {
            viewModel.deleteLocalProduct(it)
        }
        allFavProductsLayoutManager = LinearLayoutManager(this)
        recyclerView.apply {
            adapter = recyclerAdapter
            layoutManager = allFavProductsLayoutManager
        }
        viewModel.products.observe(this) {
            recyclerAdapter.submitList(it)
        }

    }
}

