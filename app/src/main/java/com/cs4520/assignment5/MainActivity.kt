package com.cs4520.assignment5

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import androidx.work.WorkManager
import com.cs4520.assignment5.View.appNavHost
import com.cs4520.assignment5.databinding.MainActivityBinding
import com.cs4520.assignment5.model.database.AppDatabase
import com.cs4520.assignment5.model.database.ProductDao
import com.cs4520.assignment5.model.database.ProductRepository
import com.cs4520.assignment5.viewmodel.ProductViewModel
import com.cs4520.assignment5.viewmodel.ProductViewModelFactory

class MainActivity: ComponentActivity(){
    private lateinit var binding: MainActivityBinding

    val dataBase by lazy { AppDatabase.getDatabase(this) }
    private lateinit var viewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application: Application = requireNotNull(this).application
        val dao: ProductDao = AppDatabase.getDatabase(application).productDao()
        val repository = ProductRepository(dao)
        val factory = ProductViewModelFactory(repository, WorkManager.getInstance(this))
        viewModel = ViewModelProvider(this, factory).get(ProductViewModel::class.java)

        setContent{
            Surface (modifier = Modifier.fillMaxSize()) {
                appNavHost(navController = rememberNavController(), viewModel = viewModel)
            }
        }
    }
}