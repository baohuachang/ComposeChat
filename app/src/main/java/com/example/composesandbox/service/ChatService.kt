package com.example.composesandbox.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.composesandbox.ChatServiceInterface
import com.example.composesandbox.OnChatInterface
import com.example.composesandbox.data.exampleContents
import java.lang.Thread.sleep
import kotlin.concurrent.thread

class ChatService : Service() {

    private val binder = object: ChatServiceInterface.Stub() {

        private var client: OnChatInterface? = null

        override fun startChat(chat: OnChatInterface?) {
            client = chat
            startChatThread()
        }

        fun onChat(author: String, content: String) {
            client?.onChat(author, content)
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    var chatThread: Thread? = null

    fun startChatThread() {
        if (chatThread != null) return

        chatThread = thread {
            while (chatThread != null) {
                sleep(((Math.random() * 3 + 1) * 1000).toLong())
                binder.onChat("John Glenn", exampleContents.random())
            }
        }
    }

    override fun onUnbind(intent: Intent?): Boolean {
        chatThread = null
        return super.onUnbind(intent)
    }
}