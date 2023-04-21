package com.example.chatgpt.models

import com.google.gson.annotations.SerializedName

data class ChatRequest(
    @SerializedName("model")
    val modelName: String,
    @SerializedName("messages")
    val messages: List<RequestMessage>
)
