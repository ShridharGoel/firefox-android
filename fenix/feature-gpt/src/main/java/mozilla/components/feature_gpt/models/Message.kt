package com.example.chatgpt.models

import com.google.gson.annotations.SerializedName


data class Message(
    @SerializedName("content") var content: String? = null
)
