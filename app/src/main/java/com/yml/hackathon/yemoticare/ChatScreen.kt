package com.yml.hackathon.yemoticare

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yml.hackathon.yemoticare.ui.component.ChatBubbleAI
import com.yml.hackathon.yemoticare.ui.component.ChatBubbleMe
import com.yml.hackathon.yemoticare.ui.component.ChatBubbleTyping
import com.yml.hackathon.yemoticare.ui.component.HintTextField

@Composable
internal fun ChatScreen(
    viewModel: MyViewModel
) {
    Column(
        Modifier
            .fillMaxHeight()
    ) {
        val scrollState = rememberLazyListState()
        val messages = viewModel.messages
        var text by rememberSaveable {
            mutableStateOf("")
        }
        LazyColumn(
            state = scrollState,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .weight(1F)
                .padding(horizontal = 10.dp),
        ) {
            item { Spacer(modifier = Modifier.padding(top = 10.dp)) }

            if (messages.isEmpty()) item { EmptyMessage() }

            items(messages) { item ->
                when (item) {
                    is ChatMessage.ME -> ChatBubbleMe(text = item.text)

                    is ChatMessage.AI -> ChatBubbleAI(text = item.text)

                    is ChatMessage.Loading -> ChatBubbleTyping()
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            HintTextField(value = text,
                modifier = Modifier.weight(1F),
                hint = "Your message",
                enabled = true,
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                onTextChanged = { text = it })
            IconButton(
                onClick = {
                    viewModel.sendMessage(text)
                    text = ""
                },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Green)
                    .padding(10.dp),
                enabled = true
            ) {
                Text(text = "Send")
            }
        }
        LaunchedEffect(messages.size) {
            scrollState.animateScrollToItem(messages.size)
        }
    }
}

@Composable
private fun EmptyMessage() {
    Text(
        text = "Hi, How can i help...",
        color = Color.White,
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.DarkGray)
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
    )
}


