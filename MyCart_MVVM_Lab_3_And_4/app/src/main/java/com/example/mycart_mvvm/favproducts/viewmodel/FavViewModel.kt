package com.example.mycart_mvvm.favproducts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycart_mvvm.model.Product
import com.example.mycart_mvvm.model.ProductRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavViewModel(private val repository: ProductRepositoryImpl): ViewModel() {
    private var _ptoducts = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _ptoducts

    init {
        getLocalProducts()

    }
    private fun getLocalProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getProducts(false).collect { products ->
                _ptoducts.postValue(products)
            }

        }
    }
    fun deleteLocalProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteProduct(product)
            getLocalProducts()
        }
    }
}