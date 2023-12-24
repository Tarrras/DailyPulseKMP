package com.foxdev.dailypulse.articles

import com.foxdev.dailypulse.articles.data.Article

data class ArticlesState(
    val articles: List<Article> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null,
)
