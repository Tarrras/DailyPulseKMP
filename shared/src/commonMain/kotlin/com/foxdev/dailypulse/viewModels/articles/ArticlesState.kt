package com.foxdev.dailypulse.viewModels.articles

import com.foxdev.dailypulse.data.models.ArticleDTO

data class ArticlesState(
    val articles: List<ArticleDTO> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null,
)
