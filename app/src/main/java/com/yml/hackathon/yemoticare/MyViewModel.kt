package com.yml.hackathon.yemoticare

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.ychat.YChat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val yChat: YChat) : ViewModel() {

    val messages = mutableStateListOf<ChatMessage>()
    val isLoading = mutableStateOf(false)

    private suspend fun completion(input: String): String {
        return try {
            yChat.completion().setInput(input).saveHistory(false).setMaxTokens(50)
                .setModel("curie:ft-y-media-labs-2023-06-02-19-29-00").execute()
        } catch (e: Exception) {
            e.printStackTrace()
            "Error"
        }
    }

    fun sendMessage(text: String) {
        isLoading.value = true
        messages.add(ChatMessage.ME(text))
        messages.add(ChatMessage.Loading)
        viewModelScope.launch {
            val ans = completion("$text . ->")
            messages.remove(ChatMessage.Loading)
            messages.add(ChatMessage.AI(ans))
            isLoading.value = false
        }
    }

}
