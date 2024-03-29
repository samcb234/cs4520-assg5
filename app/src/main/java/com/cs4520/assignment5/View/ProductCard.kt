package com.cs4520.assignment5.View


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cs4520.assignment5.R
import com.cs4520.assignment5.model.Product
import com.cs4520.assignment5.viewmodel.ProductViewModel

@Composable
fun productCard(product: Product){
    Row(
        Modifier
            .background(
                color = if (product.type == "Food") Color(android.graphics.Color.parseColor("#FFD965"))
                else Color(android.graphics.Color.parseColor("#E06666"))
            )
            .fillMaxWidth()
    ) {
        Image(painter = painterResource(id =
        if(product.type =="Food") R.drawable.food else R.drawable.equipment),
            contentDescription = "Image of Product",
            modifier = Modifier.size(70.dp))

        Column {
            Text(text = product.name)
            Text(text = if(product.expiryDate != null) product.expiryDate!! else "")
            Text(text = "\$ ${product.price.toString()}")
        }
    }
}

@Composable
fun productList(products: List<Product>){
    LazyColumn(modifier = Modifier.fillMaxSize()){
        items(products){product ->
            productCard(product = product)
        }
    }
}

//@Preview
@Composable
fun prev(viewModel: ProductViewModel){
//    val a: ArrayList<Product> = ArrayList()
//    a.add(Product.Food("product", "Food", "2020-1-1", 25.0))
//    a.add(Product.Equipment("product", "Equipment", "2020-1-1", 25.0))
//    productList(products = a)

    val loading by viewModel.loading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val products by viewModel.products.collectAsState()

    LaunchedEffect(Unit){
        viewModel.refreshProducts()
    }

    if (loading){
        Text(text = "loading")
    } else if(errorMessage != null && errorMessage != ""){
        Text(text = errorMessage!!)
    } else if(products.isEmpty()){
        Text(text = "No Products Available")
    } else{
        productList(products = products)
    }
}