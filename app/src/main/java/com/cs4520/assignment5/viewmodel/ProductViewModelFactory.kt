package com.cs4520.assignment5.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cs4520.assignment5.model.database.ProductRepository

class ProductViewModelFactory(private val repository: ProductRepository): ViewModelProvider.Factory{

    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProductViewModel::class.java)){
            return ProductViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown view model cast")
    }

}