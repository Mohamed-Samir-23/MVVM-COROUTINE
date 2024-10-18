package com.example.mycart_mvvm.allproducts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycart_mvvm.model.Product
import com.example.mycart_mvvm.model.ProductRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AllProductsViewModel(private val repository: ProductRepositoryImpl): ViewModel() {
    private var _ptoducts = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _ptoducts
    val _stateFlow = MutableStateFlow<ApiState>(ApiState.Loading())
    val stateFlow: MutableStateFlow<ApiState> = _stateFlow

    init {
        getRemoteProducts()
    }

    private fun getRemoteProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            _stateFlow.emit(ApiState.Loading())
            try {
            repository.getProducts(true).collect { products ->
                _ptoducts.postValue(products)
                _stateFlow.value = ApiState.Success(products)
            }
                } catch (e: Exception) {
                _stateFlow.value = ApiState.Failure(e)
            }
        }
    }
    fun insertLocalProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertProduct(product)
        }
    }
}

sealed class ApiState{
    class Success(val data:List<Product>) : ApiState()
    class Failure(val msg: Throwable): ApiState()
    class Loading():ApiState()
}