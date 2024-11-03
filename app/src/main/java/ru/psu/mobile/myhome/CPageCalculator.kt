package ru.psu.mobile.myhome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.psu.mobile.myhome.ui.theme.MyhomeTheme
import ru.psu.mobile.myhome.ui.theme.color_main

@Composable
fun CalculatorMenu(
    modifier: Modifier = Modifier
)
{
    val context = LocalContext.current
    var param1 by rememberSaveable  { mutableStateOf("") }
    var param2 by rememberSaveable  { mutableStateOf("") }
    var result by rememberSaveable  { mutableStateOf("") }

    val buttonShape = RoundedCornerShape(20)

    Column (
        modifier = modifier
    ){
        TextField(
            value = param1,
            onValueChange = { param1 = it },
            label = { Text("Параметр 1") }
        )
        TextField(
            value = param2,
            onValueChange = { param2 = it },
            label = { Text("Параметр 2") }
        )
        Row{
            IconButton(
                onClick = {
                    try {
                        result = (param1.toDouble()+param2.toDouble()).toString()
                    }
                    catch (_: Exception)
                    {
                        //Toast.makeText()
                    }
                },
//                colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow)
            )
            {
                Icon(
                    Icons.Outlined.Add,
                    contentDescription = "Сложить",
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                    tint = color_main
                )
            }
            FilledIconButton(
                colors = IconButtonDefaults.filledIconButtonColors(containerColor = color_main),
//                modifier = Modifier.background(color = color_main),
                shape = RoundedCornerShape(5.dp),
                onClick = {
                    try {
                        result = (param1.toDouble()-param2.toDouble()).toString()
                    }
                    catch (_: Exception)
                    {
                        //Toast.makeText()
                    }
                },

                )
            {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_remove_24),
                    contentDescription = "Сложить",
                    tint = Color.White,
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                )
            }
        }
        Text(
            text = result,
            modifier = modifier
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorPage()
{
    CalculatorMenu()
//    Modifier
//        .padding(innerPadding)
    //        Box(
//            modifier = Modifier
//                .padding(innerPadding)
//        ){
//
//        }v
}

@Preview(showBackground = true)
@Composable
fun PreviewCalculator() {
    MyhomeTheme {
        CalculatorPage()
    }
}