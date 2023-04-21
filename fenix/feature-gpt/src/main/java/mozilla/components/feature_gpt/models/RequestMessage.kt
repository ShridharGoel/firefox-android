package com.example.chatgpt.models

import com.google.gson.annotations.SerializedName

data class RequestMessage(
    @SerializedName("role")
    val role: String = "user",

    @SerializedName("content")
    val content: String = ""
)
