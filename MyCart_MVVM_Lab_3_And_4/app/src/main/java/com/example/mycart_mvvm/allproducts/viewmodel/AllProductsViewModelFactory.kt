package com.example.mycart_mvvm.allproducts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mycart_mvvm.model.ProductRepositoryImpl

class AllProductsViewModelFactory(private val repository: ProductRepositoryImpl): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AllProductsViewModel(repository) as T
    }
}