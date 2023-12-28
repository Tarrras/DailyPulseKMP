package com.foxdev.dailypulse.remote.model

import com.foxdev.dailypulse.data.models.ArticleDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleItemResponse(
    @SerialName("title")
    val title: String,
    @SerialName("source")
    val source: ArticleSourceResponse,
    @SerialName("description")
    val desc: String?,
    @SerialName("publishedAt")
    val date: String,
    @SerialName("url")
    val url: String,
    @SerialName("urlToImage")
    val imageUrl: String?
)

@Serializable
data class ArticleSourceResponse(
    val id: String?,
    val name: String
)
