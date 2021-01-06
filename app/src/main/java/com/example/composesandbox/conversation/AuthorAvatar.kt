package com.example.composesandbox.conversation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.example.composesandbox.R
import com.example.composesandbox.ui.ComposeSandboxTheme

@Composable fun AuthorAvatar(isUserMe: Boolean = false) {
    val image = imageResource(id = if (isUserMe) R.drawable.ali else R.drawable.someone_01 )
    val borderColor = if (isUserMe) MaterialTheme.colors.primary else MaterialTheme.colors.secondary
    Image(asset = image, contentScale = ContentScale.Crop, modifier =
    Modifier.clickable(onClick = { Log.i("", "AuthorAvatar: Click") })
            .padding(horizontal = 16.dp)
            .preferredSize(42.dp)
            .size(42.dp)
            .border(1.5.dp, borderColor, CircleShape)
            .border(3.dp, MaterialTheme.colors.surface, CircleShape)
            .clip(CircleShape)
    )
}

@Preview @Composable fun previewAuthorAvatar() {
    ComposeSandboxTheme() {
        AuthorAvatar(false)
    }
}