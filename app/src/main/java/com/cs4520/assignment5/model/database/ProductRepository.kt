package com.cs4520.assignment5.model.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.cs4520.assignment5.model.APIResponse

class ProductRepository(private val dao: ProductDao) {

    suspend fun insertAll(products: List<APIResponse>){
        dao.insertAll(products)
    }

    suspend fun clearDB(){
        dao.clearTable()
    }

    val products = dao.getAll()



}