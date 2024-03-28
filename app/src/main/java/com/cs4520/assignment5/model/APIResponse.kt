package com.cs4520.assignment5.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class APIResponse(@PrimaryKey var name : String,
                       @ColumnInfo(name = "type") var type: String?,
                       @ColumnInfo(name = "expiryDate") var expiryDate: String?,
                       @ColumnInfo(name = "price") var price: Double?) {}