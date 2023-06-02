package com.yml.hackathon.yemoticare

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.ychat.YChat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val yChat: YChat) : ViewModel() {

    val messages = mutableStateListOf<ChatMessage>()

    private suspend fun completion(input: String): String {
        return try {
            yChat.completion().setInput(input).saveHistory(false).setMaxTokens(1024)
                .setModel("curie:ft-y-media-labs-2023-06-01-18-47-25").execute()
        } catch (e: Exception) {
            e.printStackTrace()
            "Error"
        }
    }

    fun sendMessage(text: String) {
        messages.add(ChatMessage.ME(text))
        viewModelScope.launch {
            val ans = completion(text)
            messages.add(ChatMessage.AI(ans))
        }
    }

}
