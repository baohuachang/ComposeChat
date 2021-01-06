package com.example.composesandbox.conversation

import androidx.compose.runtime.mutableStateListOf

class Conversation (initialMessages: List<Message>, val name: String = "me") {
    private val _messages = mutableStateListOf(*initialMessages.toTypedArray())
    val messages: List<Message> = _messages

    fun add(message: Message) {
        _messages.add(message)
    }
}