package com.cs4520.assignment5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.cs4520.assignment5.View.appNavHost
import com.cs4520.assignment5.databinding.MainActivityBinding
import com.cs4520.assignment5.model.database.AppDatabase

class MainActivity: ComponentActivity(){
    private lateinit var binding: MainActivityBinding

    val dataBase by lazy { AppDatabase.getDatabase(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = MainActivityBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)

        setContent{
            Surface (modifier = Modifier.fillMaxSize()) {
                appNavHost(navController = rememberNavController())
            }
        }
    }
}