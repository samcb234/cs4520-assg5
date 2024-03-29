package com.cs4520.assignment5.View

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cs4520.assignment5.viewmodel.ProductViewModel

enum class Screen {
    LOGIN,
    PRODUCT
}

sealed class NavigationItem(val route: String){
    object Login: NavigationItem(Screen.LOGIN.name)
    object Product: NavigationItem(Screen.PRODUCT.name)
}

@Composable
fun appNavHost(modifier: Modifier = Modifier, navController: NavHostController,
               startDestination: String = NavigationItem.Login.route,
               viewModel: ProductViewModel){
    NavHost(navController = navController, startDestination = startDestination){
        composable(NavigationItem.Login.route){
            loginScreen(navController)
        }
        composable(NavigationItem.Product.route){
            prev(viewModel)
        }
    }
}