package com.example.mycart_mvvm.favproducts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mycart_mvvm.model.ProductRepositoryImpl


class FavViewModelFactory(private val repository: ProductRepositoryImpl): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavViewModel(repository) as T
    }
}