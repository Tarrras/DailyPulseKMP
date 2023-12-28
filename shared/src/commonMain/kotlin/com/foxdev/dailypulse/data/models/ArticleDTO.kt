package com.foxdev.dailypulse.data.models

data class ArticleDTO(
    val title: String,
    val desc: String,
    val date: String,
    val imageUrl: String,
    val url: String,
    val source: ArticleSource
)

data class ArticleSource(
    val id: String?,
    val name: String
)
