package com.cs4520.assignment5.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cs4520.assignment5.model.APIResponse

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun getAll(): LiveData<List<APIResponse>>

    @Query("Select * FROM product where name like :productName")
    suspend fun getSpecificProduct(productName: String):APIResponse?

    @Query("DELETE FROM product")
    suspend fun clearTable()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll( products: List<APIResponse>)
}