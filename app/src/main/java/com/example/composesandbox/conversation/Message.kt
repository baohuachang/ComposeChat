package com.example.composesandbox.conversation

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.example.composesandbox.data.exampleConversation

data class Message(val author: String, val content: String, val timestamp: String, val image: Int? = null)

@Composable fun Messages(
    messages: List<Message>,
    modifier: Modifier = Modifier,
    scrollState: ScrollState
) {
    ScrollableColumn(modifier = modifier, reverseScrollDirection = true, scrollState = scrollState) {

        messages.drop( if (messages.size > 20) messages.size -20 else 0 ).forEachIndexed { index, message ->
            val prevAuthor = messages.getOrNull(index - 1)?.author
            val nextAuthor = messages.getOrNull(index + 1)?.author
            val isFirstMessageByAuthor = prevAuthor != message.author
            val isLastMessageByAuthor = nextAuthor != message.author

            Message(message, message.author == "me", isFirstMessageByAuthor, isLastMessageByAuthor)
        }
    }
}

@Composable fun Message(message: Message, isUserMe:Boolean, isFirstMessage: Boolean, isLastMessage: Boolean) {
    Spacer(modifier = Modifier.preferredHeight(4.dp))
    when (isUserMe) {
        false -> Row(modifier = Modifier.padding(end = 16.dp).padding(vertical = 8.dp)) {
            AuthorAvatar(isUserMe)
            AuthorAndTextMessage(message)
        }
        true -> Row (modifier = Modifier.fillMaxWidth().padding(start = 16.dp).padding(vertical = 8.dp)) {
            Spacer(modifier = Modifier.weight(1f))
            AuthorAndTextMessage(message)
            AuthorAvatar(isUserMe)
        }
    }

}

@Composable fun AuthorAndTextMessage(message: Message, modifier: Modifier = Modifier) {
    Text(message.content)
}
