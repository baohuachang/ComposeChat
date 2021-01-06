package com.example.composesandbox.ui

import com.example.composesandbox.conversation.Conversation


enum class ScreenName { HOME, CONVERSATION }

sealed class Screen(val id: ScreenName) {
    object Home: Screen(ScreenName.HOME)
    data class Conversation(val conversation: com.example.composesandbox.conversation.Conversation): Screen(ScreenName.CONVERSATION)
}