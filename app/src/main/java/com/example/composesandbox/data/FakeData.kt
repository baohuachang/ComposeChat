package com.example.composesandbox.data

import com.example.composesandbox.conversation.Conversation
import com.example.composesandbox.conversation.Message

private val initialMessages = listOf(
    Message(
        "John Glenn",
        "Compose newbie: I’ve scourged the internet for tutorials about async data loading " +
                "but haven’t found any good ones. What’s the recommended way to load async " +
                "data and emit composable widgets?",
        "8:03 PM"
    ),
    Message(
        "John Glenn",
        "Compose newbie as well, have you looked at the JetNews sample? Most blog posts end up " +
                "out of date pretty fast but this sample is always up to date and deals with async " +
                "data loading (it's faked but the same idea applies) \uD83D\uDC49" +
                "https://github.com/android/compose-samples/tree/master/JetNews",
        "8:04 PM"
    ),
    Message(
        "Taylor Brooks",
        "@aliconors Take a look at the `Flow.collectAsState()` APIs",
        "8:05 PM"
    ),
    Message(
        "me",
        "You can use all the same stuff",
        "8:05 PM"
    ),
    Message(
        "me",
        "Thank you!",
        "8:06 PM",
        //R.drawable.sticker
    ),
    Message(
        "John Glenn",
        "Check it out!",
        "8:07 PM"
    )
)

var exampleContents = arrayOf(
    "What day is today?",
    "I was in the hospital for several weeks.",
    "Very please to meet you.",
    "Your friend was here a week ago, wasn't he?",
    "Yes, I do.",
    "I don't have a phonograph, either."
)

val exampleConversation = Conversation(initialMessages, "Paul Chang")
val exampleConversations: List<Conversation> =
    listOf(Conversation(initialMessages, "John Glenn"), Conversation(initialMessages, "Taylor Brooks"))