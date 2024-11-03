package ru.psu.mobile.myhome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.psu.mobile.myhome.ui.theme.MyhomeTheme
import ru.psu.mobile.myhome.ui.theme.color_main

@Composable
fun CPage1(
    navController                           : NavController?
                                            = null
) {
    Column {
        Text(
            text = "Элемент 1",
            color = color_main,
            modifier = Modifier.fillMaxWidth(),
        )
        Text(
            text = "Элемент 2",
            color = color_main,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = color_main),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController?.navigate("Страница 2")
            }
        ) {
            Text(
                text = "Переход на страницу 2",
                color = Color.White
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewPage1() {
    MyhomeTheme {
        CPage1()
    }
}