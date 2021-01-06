package com.example.composesandbox

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import com.example.composesandbox.conversation.*
import com.example.composesandbox.data.exampleConversations
import com.example.composesandbox.service.ChatService
import com.example.composesandbox.ui.ComposeSandboxTheme
import com.example.composesandbox.ui.Screen
import com.example.composesandbox.ui.typography
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindChatService()

        setContent {
            ComposeSandboxTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Layouts()
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    val serviceConnection = object: ServiceConnection {

        lateinit var chatService: ChatServiceInterface

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            chatService = service as ChatServiceInterface
            chatService.startChat(object: OnChatInterface {
                override fun asBinder(): IBinder? {
                    return null
                }

                override fun onChat(author: String?, content: String?) {
                    exampleConversations[0].add(Message(author!!, content!!, Date().toString()))
                }
            })
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            TODO("Not yet implemented")
        }
    }

    fun bindChatService() {
        Intent(this, ChatService::class.java).also { intent ->
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }
}

@Composable
fun Layouts() {
    val navigation = remember { mutableStateOf<Screen>(Screen.Home) }
    when (navigation.value) {
        is Screen.Home -> Home(navigateTo =  navigation.component2() )
        is Screen.Conversation -> { ConversationPage(navigateTo = navigation.component2(),
            (navigation.value as Screen.Conversation).conversation) }
    }
}

@Composable
fun ConversationPage(navigateTo: (Screen) -> Unit, conversation: Conversation) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("${conversation.name}")
        },
            actions = {
                IconButton(onClick = {
                    navigateTo(Screen.Home)
                }) {
                    Icon(Icons.Default.Close)
                }
            }
        )
    }) {
        Column {
            val scrollState = rememberScrollState(0f)
            Messages(messages = conversation.messages, modifier = Modifier.weight(1f), scrollState)
            UserInput(conversation, scrollState)
        }
    }
}

@Composable
fun Home(navigateTo: (Screen) -> Unit) {
    Scaffold(
            topBar = {
                TopAppBar(
                        title = {
                            Text("Chat")
                        },
                        actions = {
                            IconButton(onClick = {}) {
                                Icon(Icons.Filled.Add)
                            }
                        }
                )
            },
            bottomBar = {
                BottomNavigation() {
                    BottomNavigationItem(icon = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            //Icon(Icons.Filled.Chat)
                            Icon(Icons.Filled.Share)
                            Text("Chat")
                        }
                    }, selected = true, onClick = {})
                    BottomNavigationItem(icon = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            //Icon(Icons.Filled.Contacts)
                            Icon(Icons.Filled.Call)
                            Text("Contacts")
                        }
                    }, selected = false, onClick = {})
                    BottomNavigationItem(icon = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            //Icon(Icons.Filled.Map)
                            Icon(Icons.Filled.LocationOn)
                            Text("Discovery")
                        }
                    }, selected = false, onClick = {})
                    BottomNavigationItem(icon = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Filled.Person)
                            Text("Me")
                        }
                    }, selected = false, onClick = {})
                }
                //BottomAppBar() {}
            }
    ) { innerPadding ->
        BodyContent(navigateTo, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun BodyContent(navigateTo: (Screen) -> Unit, modifier: Modifier = Modifier) {
    ChatPage(modifier = modifier, search = {
        Search(Modifier.fillMaxWidth().background(Color.LightGray).padding(4.dp))
    }) {
        Conversations(navigateTo, conversations = exampleConversations)
        //Conversations(conversations = exampleConversations)
    }
}

@Composable
fun UserInput(conversation: Conversation, scrollState: ScrollState) {
    val textRemember = remember { mutableStateOf("How are you?") }

    Row {
        TextField(value = textRemember.value, onValueChange = {
            textRemember.value = it
        }, placeholder = {
            Text("Send Message")
        }, textStyle = typography.body1,
            modifier = Modifier.preferredHeight(40.dp).weight(1f),
            backgroundColor = Color.White
        )

        Button(
            onClick = {
                conversation.add(Message("me", textRemember.value, ""))
                //scrollState.smoothScrollTo(0f)
                //textRemember.value = ""
            }
            ,modifier = Modifier.preferredHeight(60.dp)
        ) {
            Text(text = "Send")
        }
    }
    /*
    Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
    ) {

        TextField(value = textRemember.value, onValueChange = {
            textRemember.value = it
        }, placeholder = {
            Text("Send Message")
        }, textStyle = typography.body1, modifier = Modifier.weight(1f).preferredHeight(30.dp))

        //Spacer(modifier = Modifier.weight(1f))

        Button(enabled = textRemember.value.isNotEmpty(),
                onClick = {
                    //conversation.add(Message("me", textRemember.value, ""))
                    textRemember.value = ""
                }
        ,modifier = Modifier.preferredHeight(30.dp)
        ) {
            Text(text = "Send")
        }
    }*/
}
