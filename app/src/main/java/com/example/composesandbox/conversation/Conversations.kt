package com.example.composesandbox.conversation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.composesandbox.R
import com.example.composesandbox.ui.Screen
import com.example.composesandbox.ui.typography


@Composable
fun ChatPage(
    modifier: Modifier,
    search: @Composable() () -> Unit,
    conversations: @Composable() () -> Unit
) {
    ScrollableColumn(modifier = modifier.padding(8.dp),
        reverseScrollDirection = true,
        scrollState = rememberScrollState(1f)) {
        search()
        conversations()
    }
}

@Composable
fun Search(modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.weight(1f))
        Icon(Icons.Default.Search)
        Text(text = "Search")
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun Conversations(
    navigateTo: (Screen) -> Unit,
    conversations: List<Conversation>,
    itemModifier: Modifier = Modifier
) {
    conversations.forEach { conversation ->
        ConversationWidget(
            conversation,
            modifier = itemModifier.clickable(onClick = {
                navigateTo(
                    Screen.Conversation(conversation)
                )
            })
        )
    }
}

@Composable
fun ConversationWidget(conversation: Conversation, modifier: Modifier = Modifier) {
    Row(modifier = modifier.padding(8.dp)) {
        Image(
            imageResource(R.drawable.ali), contentScale = ContentScale.Crop,
            modifier = Modifier.preferredSize(60.dp).clip(RoundedCornerShape(4.dp))
        )
        Spacer(modifier = Modifier.preferredWidth(16.dp))
        Column() {
            Text(conversation.name, style = typography.h6, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.preferredHeight(8.dp))
            Text(
                "[${conversation.messages.size} messages] ${conversation.messages[0].author}:${conversation.messages[0].content}",
                maxLines = 1,
                style = typography.body2
            )
        }
    }
    Divider(color = Color.LightGray)
}