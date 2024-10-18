package ru.psu.mobile.myhome

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CScaffold()
{
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "Страница 1",
        builder = {
            composable("Страница 1") {
                CPage1(navController = navController)
            }
            composable("Страница 2") {
                CPage2(navController = navController)
            }
            composable("Страница 3") {
                CalculatorPage()
            }
        }
    )

//    Scaffold(topBar = {
//        TopAppBar(
//            colors = topAppBarColors(
//                containerColor = MaterialTheme.colorScheme.primaryContainer,
//                titleContentColor = MaterialTheme.colorScheme.primary,
//            ),
//            title = {
//                Text("Top app bar")
//            }
//        )
//    },
//        bottomBar = {
//            BottomAppBar(
//                containerColor = MaterialTheme.colorScheme.primaryContainer,
//                contentColor = MaterialTheme.colorScheme.primary,
//            ) {
//                Text(
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    textAlign = TextAlign.Center,
//                    text = "Bottom app bar",
//                )
//            }
//        }
//
//    ) {
//        innerPadding ->
//
//        NavHost(
//
//
//        )
//
//
//    }
}