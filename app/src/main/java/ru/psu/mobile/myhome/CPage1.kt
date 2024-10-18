package ru.psu.mobile.myhome

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun CPage1(
    navController                           : NavController
)
{
    Button(onClick = {
        navController.navigate("Страница 2")
    }) {
        Text(text = "Переход на страницу 2")
    }

}