package com.aichat.gemini.model

data class ChatSession(
    val id: Long = 0,
    val userId: Long,
    var title: String,
    val createdAt: Long = System.currentTimeMillis()
)
