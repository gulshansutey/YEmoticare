package com.yml.hackathon.yemoticare

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.ychat.entrypoint.features.ChatCompletions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yml.hackathon.yemoticare.utils.Utils.readJsonFile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val chatCompletions: ChatCompletions) : ViewModel() {

    val messages = mutableStateListOf<ChatMessage>()
    val isLoading = mutableStateOf(false)

    init {
        messages.add(ChatMessage.AI("Hi, How can i help..."))
    }

    fun getModelData(context: Context) {
        viewModelScope.launch {
            val json = context.readJsonFile("training.json")
            val results: List<ChatBotContext> =
                Gson().fromJson(json, object : TypeToken<List<ChatBotContext>>() {}.type)
            results.forEach {
                chatCompletions.addMessage(role = it.type, content = it.message)
            }
        }
    }

    fun sendMessage(text: String) {
        isLoading.value = true
        messages.add(ChatMessage.ME(text))
        messages.add(ChatMessage.Loading)
        viewModelScope.launch {
            completion(text)
            messages.remove(ChatMessage.Loading)
            isLoading.value = false
        }
    }

    private suspend fun completion(s: String) {
        runCatching { chatCompletions.execute(s) }
            .onSuccess {
                messages.add(ChatMessage.AI(it.first().content))
            }
            .onFailure {
                Log.d("Answer", it.message.toString())
            }
    }

}
