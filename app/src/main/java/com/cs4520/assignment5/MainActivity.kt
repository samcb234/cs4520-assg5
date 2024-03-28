package com.cs4520.assignment5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cs4520.assignment5.databinding.MainActivityBinding
import com.cs4520.assignment5.model.database.AppDatabase

class MainActivity: AppCompatActivity(){
    private lateinit var binding: MainActivityBinding

    val dataBase by lazy { AppDatabase.getDatabase(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}