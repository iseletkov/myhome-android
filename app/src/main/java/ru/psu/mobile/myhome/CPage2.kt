package ru.psu.mobile.myhome

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ru.psu.mobile.myhome.model.CMessage
import java.time.LocalDateTime
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.psu.mobile.myhome.ui.theme.MyhomeTheme
import ru.psu.mobile.myhome.ui.theme.color_main
import ru.psu.mobile.myhome.viewmodel.CViewModelChat
import java.util.UUID

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MessageView(
    message : CMessage,
    onDeleteClick : (UUID) -> Unit,
    onEditClick : (UUID) -> Unit,
)
{
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.pointerInput(Unit){
            detectTapGestures(
                onDoubleTap = {
                    expanded = !expanded
                }
            )
        }
    ){
        Row{
          Text(message.dateTime.toString())
          Text("Это автор")
        }
        Text(
            message.text
        )
        DropdownMenu(expanded = expanded,
            onDismissRequest = { expanded = false }) {
            Text(
                "Удалить",
                modifier = Modifier
                    .combinedClickable (
                        onClick = {
                            onDeleteClick(message.id)
                        }
                    )
            )
            Text(
                "Редактировать",
                modifier = Modifier
                    .combinedClickable (
                        onClick = {
                            onEditClick(message.id)
                        }
                    )
            )
        }

    }
}
@Composable
fun MessagePanel(
    viewModel                               : CViewModelChat,
    listState                               : LazyListState
)
{
    val coroutineScope                      = rememberCoroutineScope()

    Row(
        modifier = Modifier.fillMaxWidth()
    ){
        TextField(
            value = viewModel.newMessage,
            onValueChange = {text ->
                viewModel.updateNewMessage(text)
            },
            label = {
                Text("Сообщение")
            },
            modifier = Modifier.weight(1f)
        )
        FilledIconButton(
            colors = IconButtonDefaults.filledIconButtonColors(containerColor = color_main),
//                modifier = Modifier.background(color = color_main),
            shape = RoundedCornerShape(5.dp),
            onClick = {
                viewModel.sendMessage()
                coroutineScope.launch {
                    listState.animateScrollToItem(
                        index = listState.layoutInfo.totalItemsCount - 1
                    )
                }
            },
        )
        {
            Icon(
                painter = painterResource(id = R.drawable.baseline_send_24),
                contentDescription = "Отправить",
                tint = Color.White,
                modifier = Modifier.size(ButtonDefaults.IconSize),
            )
        }

    }
}

@Composable
fun CPageChat(
    navController                           : NavController?
                                            = null
)
{
    val context                             = LocalContext.current
    val vm                                  : CViewModelChat
                                            = viewModel()

    val listState                           = rememberLazyListState()

    //Начальная прокрутка в конец списка сообщений
    LaunchedEffect(Unit) {
        listState.animateScrollToItem(
            index                           = listState.layoutInfo.totalItemsCount - 1
        )
    }
    //Подписка и отображение потока информационных сообщений
    val toastMessage by vm.toastMessageFlow.collectAsStateWithLifecycle(
        initialValue = null
    )

    when (toastMessage) {
        null                                -> {

        }
        else                                -> {
            Toast.makeText(
                context,
                toastMessage!!.string,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Scaffold(
        bottomBar = {
            MessagePanel(vm, listState)
        }
    ) { innerPadding->
        LazyColumn (
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.padding(innerPadding),
            state = listState
        )
        {
            items(
                vm.messages,
                key = {message ->
                    message.id
                }
            ) { message ->
                MessageView(
                    message,
                    onDeleteClick = { id ->
                        vm.deleteMessageById(id)
                    },
                    onEditClick = {id ->
                        vm.startEditMessage(id)
                    }
                )

            }
        }
    }




}

@Preview(showBackground = true)
@Composable
fun PreviewChat() {
    MyhomeTheme {
        CPageChat()
    }
}