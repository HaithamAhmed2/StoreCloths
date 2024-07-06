package com.store.cloths.models

data class Review(
    val id: Long,
    val name: String,
    val grade: Float,
    val text: String,
)