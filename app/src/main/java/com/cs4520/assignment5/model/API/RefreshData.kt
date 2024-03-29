package com.cs4520.assignment5.model.API

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.cs4520.assignment5.model.database.AppDatabase.Companion.getDatabase
import com.cs4520.assignment5.model.database.ProductRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownHostException

class RefreshData(val context: Context, workerParameters: WorkerParameters): CoroutineWorker(context, workerParameters) {

    val retrofit = Retrofit.Builder()
        .baseUrl(Api.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(APIService::class.java)

    override suspend fun doWork(): Result = withContext(Dispatchers.IO){
        val db = getDatabase(applicationContext)
        val productRepository = ProductRepository(db.productDao())

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
                }
                println("added data")
                Result.success()
            }
            else{
                Result.failure(Data.Builder().putString("error", response.message()).build())
            }
        } catch (e: UnknownHostException){
            println("no internet")
            Result.failure(Data.Builder().putString("error", "no internet").build())
        }
    }
}