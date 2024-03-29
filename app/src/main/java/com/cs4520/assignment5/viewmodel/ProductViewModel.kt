package com.cs4520.assignment5.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cs4520.assignment5.model.API.APIService
import com.cs4520.assignment5.model.API.Api
import com.cs4520.assignment5.model.APIResponse
import com.cs4520.assignment5.model.Product
import com.cs4520.assignment5.model.database.ProductRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownHostException

class ProductViewModel(private val productRepository: ProductRepository): ViewModel() {

    init {
        productRepositoryObserve()
    }

    val retrofit = Retrofit.Builder()
        .baseUrl(Api.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(APIService::class.java)


//    private val _loading = MutableLiveData<Boolean>(false)
//    val loading: LiveData<Boolean> = _loading
//
//    private val _products = MutableLiveData<List<Product>>()
//    val products: LiveData<List<Product>> = _products
//
//    private val _errorMessage = MutableLiveData<String?>(null)
//    val errorMessage: LiveData<String?> = _errorMessage

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products = _products.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

     fun refreshProducts(){
         _loading.value = true
         val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
             throwable.printStackTrace()
         }
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            try{
                val response = retrofit.getProducts()
                if(response.isSuccessful){
                    if(!response.body().isNullOrEmpty()){
                        val filteredResponse = response.body()!!.filter {
                            if(it.type == "Food"){
                                return@filter (it.price != null) && (it.expiryDate != null)
                            }
                            else{
                                return@filter (it.price != null)
                            }
                        }
                        productRepository.insertAll(filteredResponse)
                        println("adding products")
                    }
                    launch(Dispatchers.Main){
                        _errorMessage.value = null
                    }
                }
                else{
                    println("non 200 response")
                    launch(Dispatchers.Main){
                        _errorMessage.value = response.message()
                    }
                }
            } catch (e: UnknownHostException){
                println("no internet")
            }

            launch(Dispatchers.Main){
                _loading.value = false
            }
        }
     }

    private fun convertToSealed(startList: List<APIResponse>?): List<Product>{
        if(startList == null){
            return listOf()
        }
        return startList.map{
            when(it.type){
                "Food" -> Product.Food(it.name, it.type, it.expiryDate, it.price)
                "Equipment" -> Product.Equipment(it.name, it.type, it.expiryDate, it.price)
                else -> {Product.Equipment(it.name, "Equipment", it.expiryDate, it.price)}
            }
        }
    }

    private fun productRepositoryObserve(){
        productRepository.products.observeForever(){
            val out: List<Product> = convertToSealed(it)
            _products.value = out
        }
    }
}