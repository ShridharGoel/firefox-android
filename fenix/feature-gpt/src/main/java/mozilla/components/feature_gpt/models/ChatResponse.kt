package com.example.chatgpt.models

import com.google.gson.annotations.SerializedName

data class ChatResponse(
    @SerializedName("choices") var choices: ArrayList<Choices> = arrayListOf()
)
