package ru.psu.mobile.myhome.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.psu.mobile.myhome.model.CMessage
import ru.psu.mobile.myhome.model.CToastMessage
import java.time.LocalDateTime
import java.util.UUID

class CViewModelChat : ViewModel()  {

    private val _toastMessageFlow           = MutableSharedFlow<CToastMessage?>(1)
    val toastMessageFlow                    = _toastMessageFlow.asSharedFlow()


    val messages = mutableStateListOf(
        CMessage(UUID.randomUUID(), "Сообщение 1"),
        CMessage(UUID.randomUUID(), "Сообщение 2"),
        CMessage(UUID.randomUUID(), "Сообщение 100500"),
        CMessage(UUID.randomUUID(), "Это очень длинное сообщение с очень большим количество различных слов и символов для проверки внешнего вида переносов", LocalDateTime.now()),
        CMessage(UUID.randomUUID(), "adawd awd aervrtved aw", LocalDateTime.now()),
        CMessage(UUID.randomUUID(), "adawd awd ad aw", LocalDateTime.now()),
        CMessage(UUID.randomUUID(), "adadj8ij8fo 7i 7 j 7j9 7j jyijij7j7878j j 78j 78j78j8 8 7 78 7 j8tj86j78 jwd awwrtvwrd advd aw", LocalDateTime.now()),
        CMessage(UUID.randomUUID(), "adawd awd ad aw", LocalDateTime.now()),
        CMessage(UUID.randomUUID(), "adawd wrvrtvrawd ad aw", LocalDateTime.now()),
        CMessage(UUID.randomUUID(), "adawd awd ad aw", LocalDateTime.now()),
        CMessage(UUID.randomUUID(), "adawd awrtvwrtvd ad aw", LocalDateTime.now()),
        CMessage(UUID.randomUUID(), "adawd aqwrcrtwd ad aw", LocalDateTime.now()),
        CMessage(UUID.randomUUID(), "adawd aslfkml lsefk slkef slefslefswwrtvrtvd ad aw", LocalDateTime.now()),
        CMessage(UUID.randomUUID(), "adawd awd aervrtved aw", LocalDateTime.now()),
        CMessage(UUID.randomUUID(), "adawd awd ad aw", LocalDateTime.now()),
        CMessage(UUID.randomUUID(), "adadj8ij8fo 7i 7 j 7j9 7j jyijij7j7878j j 78j 78j78j8 8 7 78 7 j8tj86j78 jwd awwrtvwrd advd aw", LocalDateTime.now()),
        CMessage(UUID.randomUUID(), "adawd awd ad aw", LocalDateTime.now()),
        CMessage(UUID.randomUUID(), "adawd wrvrtvrawd ad aw", LocalDateTime.now()),
        CMessage(UUID.randomUUID(), "adawd awd ad aw", LocalDateTime.now()),
        CMessage(UUID.randomUUID(), "adawd awrtvwrtvd ad aw", LocalDateTime.now()),
        CMessage(UUID.randomUUID(), "adawd aqwrcrtwd ad aw", LocalDateTime.now()),
        CMessage(UUID.randomUUID(), "adawd aslfkml lsefk slkef slefslefswwrtvrtvd ad aw", LocalDateTime.now()),
        CMessage(UUID.randomUUID(), "adawd awd aervrtved aw", LocalDateTime.now()),
        CMessage(UUID.randomUUID(), "adawd awd ad aw", LocalDateTime.now()),
        CMessage(UUID.randomUUID(), "adadj8ij8fo 7i 7 j 7j9 7j jyijij7j7878j j 78j 78j78j8 8 7 78 7 j8tj86j78 jwd awwrtvwrd advd aw", LocalDateTime.now()),
        CMessage(UUID.randomUUID(), "adawd awd ad aw", LocalDateTime.now()),
        CMessage(UUID.randomUUID(), "adawd wrvrtvrawd ad aw", LocalDateTime.now())

    )



    fun deleteMessage(message: CMessage){
        messages.remove(message)
    }

    fun deleteMessageById(id :UUID)
    {
        val index                           = messages.indexOfFirst { message ->
            message.id == id
        }
        if (index>=0)
        {
            messages.removeAt(index)
        }
    }

    var newMessage by mutableStateOf("")
        private set
    fun updateNewMessage(
        input                               : String
    ) {
        newMessage                          = input
    }

    fun sendMessage()
    {
        if (idToEdit!=null)
        {
            val index                       = messages.indexOfFirst { message ->
                message.id == idToEdit
            }
            if (index<0) {
                viewModelScope.launch {
                    _toastMessageFlow.emit(CToastMessage("Элемент уже удалён!"))
                }

                idToEdit = null
            }
            else {
                val message1 = messages[index].copy()
                message1.text = newMessage
                messages[index] = message1
            }
        }
        else {
            val message                     = CMessage(
                UUID.randomUUID(),
                newMessage,
                author = "Я"
            )
            messages.add(message)
//            viewModelScope.launch {
//                _toastMessageFlow.emit(CToastMessage("Сообщение отправлено!"))
//            }
        }
        updateNewMessage("")
        return
    }

    var idToEdit                            : UUID?
                                            = null
    fun startEditMessage(
        id                                  : UUID
    )
    {
        val index                           = messages.indexOfFirst { message ->
            message.id == id
        }
        if (index<0)
            return

        idToEdit                            = id

        updateNewMessage(messages[index].text)
        return
    }
}