package com.example.chatgpt.models

import com.google.gson.annotations.SerializedName

data class Choices(
    @SerializedName("message") var message: Message? = Message()
)
