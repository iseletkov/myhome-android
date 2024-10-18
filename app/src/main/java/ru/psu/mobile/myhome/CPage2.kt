package ru.psu.mobile.myhome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ru.psu.mobile.myhome.model.CMessage
import java.time.LocalDateTime
import androidx.compose.foundation.lazy.items

@Composable
fun MessageView(
    message : CMessage
)
{
    Column{
        Row{
          Text(message.dateTime.toString())
          Text("Это автор")
        }
        Text(message.text)

    }
}
@Composable
fun CPage2(
    navController                           : NavController
)
{
    val messages = listOf(
        CMessage("adawd awd ad aw", LocalDateTime.now()),
        CMessage("adawd awrtvwrtvd ad aw", LocalDateTime.now()),
        CMessage("adawd aqwrcrtwd ad aw", LocalDateTime.now()),
        CMessage("adawd aslfkml lsefk slkef slefslefswwrtvrtvd ad aw", LocalDateTime.now()),
        CMessage("adawd awd aervrtved aw", LocalDateTime.now()),
        CMessage("adawd awd ad aw", LocalDateTime.now()),
        CMessage("adadj8ij8fo 7i 7 j 7j9 7j jyijij7j7878j j 78j 78j78j8 8 7 78 7 j8tj86j78 jwd awwrtvwrd advd aw", LocalDateTime.now()),
        CMessage("adawd awd ad aw", LocalDateTime.now()),
        CMessage("adawd wrvrtvrawd ad aw", LocalDateTime.now()),
        CMessage("adawd awd ad aw", LocalDateTime.now()),
        CMessage("adawd awrtvwrtvd ad aw", LocalDateTime.now()),
        CMessage("adawd aqwrcrtwd ad aw", LocalDateTime.now()),
        CMessage("adawd aslfkml lsefk slkef slefslefswwrtvrtvd ad aw", LocalDateTime.now()),
        CMessage("adawd awd aervrtved aw", LocalDateTime.now()),
        CMessage("adawd awd ad aw", LocalDateTime.now()),
        CMessage("adadj8ij8fo 7i 7 j 7j9 7j jyijij7j7878j j 78j 78j78j8 8 7 78 7 j8tj86j78 jwd awwrtvwrd advd aw", LocalDateTime.now()),
        CMessage("adawd awd ad aw", LocalDateTime.now()),
        CMessage("adawd wrvrtvrawd ad aw", LocalDateTime.now()),
        CMessage("adawd awd ad aw", LocalDateTime.now()),
        CMessage("adawd awrtvwrtvd ad aw", LocalDateTime.now()),
        CMessage("adawd aqwrcrtwd ad aw", LocalDateTime.now()),
        CMessage("adawd aslfkml lsefk slkef slefslefswwrtvrtvd ad aw", LocalDateTime.now()),
        CMessage("adawd awd aervrtved aw", LocalDateTime.now()),
        CMessage("adawd awd ad aw", LocalDateTime.now()),
        CMessage("adadj8ij8fo 7i 7 j 7j9 7j jyijij7j7878j j 78j 78j78j8 8 7 78 7 j8tj86j78 jwd awwrtvwrd advd aw", LocalDateTime.now()),
        CMessage("adawd awd ad aw", LocalDateTime.now()),
        CMessage("adawd wrvrtvrawd ad aw", LocalDateTime.now())
    )

//    Button(onClick = {
//        navController.navigate("Страница 3")
//    }) {
//        Text(text = "Переход на страницу 3")
//    }


    LazyColumn {
        items(messages) { message ->
            MessageView(message)
        }
    }
}