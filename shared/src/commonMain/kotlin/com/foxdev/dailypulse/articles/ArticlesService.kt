package com.foxdev.dailypulse.articles

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticlesService(
    private val httpClient: HttpClient
) {
    private val country = "US"
    private val category = "business"
    private val apiKey = "bc19baf97fea4a24a4a5b3f77da12f54"

    suspend fun fetchArticles(): List<ArticleRaw> {
        val response: ArticlesResponse =
            httpClient.get("https://newsapi.org/v2/top-headlines?country=$country&category=$category&apiKey=$apiKey")
                .body()
        return response.articles
    }
}