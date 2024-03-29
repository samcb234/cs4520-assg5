package com.cs4520.assignment5.View

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController


@Composable
fun loginScreen(navController: NavController){
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = username, onValueChange = {username = it},
            placeholder = {Text("username")})

        TextField(value = password, onValueChange = {password = it},
            placeholder = {Text("password")})

        Button(onClick = {
            if (username == "admin" && password == "admin"){
                username = ""
                password = ""
                navController.navigate(Screen.PRODUCT.name)
            } else{
                username = ""
                password = ""
                Toast.makeText(context, "Please enter valid username and password", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text(text = "login")
        }
    }
}

