package com.cs4520.assignment5.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


sealed class Product(open val name: String,
                     open val type: String?,
                     open val expiryDate: String?,
                     open val price: Double?) {


    data class Food(override val name: String,
                    override val type: String?,
                    override val expiryDate: String?,
                    override val price: Double?) : Product(name, type, expiryDate, price){

    }

    data class Equipment(override val name: String,
                    override val type: String?,
                    override val expiryDate: String?,
                    override val price: Double?) : Product(name, type, expiryDate, price){

    }

}