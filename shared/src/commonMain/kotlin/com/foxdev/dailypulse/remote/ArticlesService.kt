package com.foxdev.dailypulse.remote

import com.foxdev.dailypulse.remote.model.ArticleItemResponse
import com.foxdev.dailypulse.remote.model.ArticlesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticlesService(
    private val httpClient: HttpClient
) {
    private val country = "US"
    private val category = "business"
    private val apiKey = "bc19baf97fea4a24a4a5b3f77da12f54"

    suspend fun fetchTopHeadlineArticles(
        country: String = this.country,
        category: String = this.category,
        pageSize: Int = 20,
    ): List<ArticleItemResponse> {
        val response: ArticlesResponse =
            httpClient.get("https://newsapi.org/v2/top-headlines?country=$country&category=$category&pageSize=$pageSize&apiKey=$apiKey")
                .body()
        return response.articles
    }

    suspend fun fetchRegularArticles(
        country: String = this.country,
        category: String = this.category,
        pageSize: Int = 20,
    ): List<ArticleItemResponse> {
        val response: ArticlesResponse =
            httpClient.get("https://newsapi.org/v2/everything?country=$country&category=$category&pageSize=$pageSize&apiKey=$apiKey")
                .body()
        return response.articles
    }
}