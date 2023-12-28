package com.foxdev.dailypulse.db.model

data class ArticleEntity(
    val title: String,
    val desc: String?,
    val date: String,
    val imageUrl: String?,
    val url: String,
    val source: ArticleSourceEntity
)

data class ArticleSourceEntity(
    val id: String?,
    val name: String
)