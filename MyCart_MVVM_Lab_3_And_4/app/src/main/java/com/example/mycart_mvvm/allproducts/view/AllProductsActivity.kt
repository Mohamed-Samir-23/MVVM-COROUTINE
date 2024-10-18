package com.example.mycart_mvvm.allproducts.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycart_mvvm.DataBase.ProductsLocalDataSourceImpl
import com.example.mycart_mvvm.R
import com.example.mycart_mvvm.allproducts.viewmodel.AllProductsViewModel
import com.example.mycart_mvvm.allproducts.viewmodel.AllProductsViewModelFactory
import com.example.mycart_mvvm.allproducts.viewmodel.ApiState
import com.example.mycart_mvvm.model.ProductRepositoryImpl
import com.example.mycart_mvvm.network.RetrofitHelper
import com.example.mykotlinapp1.DataBase.AppDataBase
import com.example.mykotlinapp1.network.DummyService
import com.example.productskotlin.network.NetworkConnectionStatus
import com.example.productskotlin.network.ProductsRemoteDataSourceImpl
import kotlinx.coroutines.launch

class AllProductsActivity : AppCompatActivity() {
    private lateinit var recyclerAdapter: AllProductsAdapter
    private lateinit var networkStatus: NetworkConnectionStatus
    private lateinit var viewModel: AllProductsViewModel
    lateinit var allProductsLayoutManager: LinearLayoutManager
    lateinit var recyclerView: RecyclerView
    lateinit var allProductFactory: AllProductsViewModelFactory
    private lateinit var imageViewStates: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_all_products)
        imageViewStates = findViewById(R.id.imageViewStates)

        allProductFactory=AllProductsViewModelFactory(
            ProductRepositoryImpl.getInstance(
                ProductsRemoteDataSourceImpl.getInstance(RetrofitHelper.getInstance().create(
                    DummyService::class.java)),
                ProductsLocalDataSourceImpl(AppDataBase.getInstance(this).getProductDAO())
            ))

        viewModel = ViewModelProvider(this, allProductFactory).get(AllProductsViewModel::class.java)
        recyclerView= findViewById(R.id.recycViewAll)

        recyclerAdapter = AllProductsAdapter(this) {
            viewModel.insertLocalProduct(it)
        }

        allProductsLayoutManager = LinearLayoutManager(this)
        recyclerView.apply {
            adapter = recyclerAdapter
            layoutManager = allProductsLayoutManager
        }
        viewModel.products.observe(this) {
            recyclerAdapter.submitList(it)
        }
        // Collect StateFlow and handle UI updates based on state
        lifecycleScope.launch {
            viewModel.stateFlow.collect { state ->
                when (state) {
                    is ApiState.Loading -> {
                        imageViewStates.visibility = View.VISIBLE
                        imageViewStates.setImageDrawable(ContextCompat.getDrawable(this@AllProductsActivity, R.drawable.downloading))
                    }

                    is ApiState.Success -> {
                        imageViewStates.visibility = View.GONE
                        recyclerAdapter.submitList(state.data)
                    }

                    is ApiState.Failure -> {
                        imageViewStates.visibility = View.VISIBLE
                        imageViewStates.setImageDrawable(ContextCompat.getDrawable(this@AllProductsActivity, R.drawable.no_internet))
                    }
                }
            }
        }


    }

}
