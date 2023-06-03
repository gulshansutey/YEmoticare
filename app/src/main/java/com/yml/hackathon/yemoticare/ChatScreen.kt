package com.yml.hackathon.yemoticare

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.yml.hackathon.yemoticare.ui.component.ChatBubbleAI
import com.yml.hackathon.yemoticare.ui.component.ChatBubbleMe
import com.yml.hackathon.yemoticare.ui.component.ChatBubbleTyping
import com.yml.hackathon.yemoticare.ui.component.HintTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ChatScreen(
    viewModel: MyViewModel,
    onBackPressed: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
    ) {
        val scrollState = rememberLazyListState()
        val messages = viewModel.messages
        val focusManager = LocalFocusManager.current
        val isLoading by rememberSaveable {
            viewModel.isLoading
        }
        var text by rememberSaveable {
            mutableStateOf("")
        }
        TopAppBar(title = { Text(text = "Y-Emoticare") }, navigationIcon = {
            Icon(imageVector = Icons.Filled.ArrowBack,
                contentDescription = "back",
                Modifier
                    .padding(10.dp)
                    .clickable {
                        onBackPressed()
                    }
            )
        })
        LazyColumn(
            state = scrollState,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .weight(1F)
                .padding(horizontal = 10.dp),
        ) {
            item { Spacer(modifier = Modifier.padding(top = 10.dp)) }

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
                enabled = !isLoading,
                keyboardActions = KeyboardActions(onSend = {
                    if (text.isNotEmpty() && !isLoading) {
                        viewModel.sendMessage(text)
                        text = ""
                        focusManager.clearFocus()
                    }
                }),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    imeAction = ImeAction.Send
                ),
                onTextChanged = { text = it })
//                IconButton(
//                    onClick = {
//                        if (text.isNotEmpty() && !isLoading) {
//                            viewModel.sendMessage(text)
//                            text = ""
//                            focusManager.clearFocus()
//                        }
//                    },
//                    modifier = Modifier
//                        .clip(CircleShape)
//                        .background(Color.Green)
//                        .padding(10.dp),
//                    enabled = true
//                ) {
//                    Icon(imageVector = Icons.Filled.Send, "Send")
//                }
        }

        LaunchedEffect(messages.size) {
            scrollState.animateScrollToItem(messages.size)
        }
    }
}


