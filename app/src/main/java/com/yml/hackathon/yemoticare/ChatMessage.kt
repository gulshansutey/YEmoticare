package com.yml.hackathon.yemoticare

sealed class ChatMessage {
    data class ME(val text: String) : ChatMessage()
    data class AI(val text: String) : ChatMessage()
    object Loading : ChatMessage()
}