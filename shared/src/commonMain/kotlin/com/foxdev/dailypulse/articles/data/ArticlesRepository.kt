package com.foxdev.dailypulse.articles.data

import com.foxdev.dailypulse.articles.remote.ArticleRaw
import com.foxdev.dailypulse.articles.remote.ArticlesService

class ArticlesRepository(
    private val localDataSource: ArticlesDataSource,
    private val service: ArticlesService
) {

    suspend fun getArticles(forceFetch: Boolean): List<ArticleRaw> {
        if (forceFetch) {
            localDataSource.clearArticles()
            return fetchArticles()
        }

        val localArticles = localDataSource.getAllArticles()

        if (localArticles.isEmpty()) {
            return fetchArticles()
        }

        return localArticles
    }

    private suspend fun fetchArticles(): List<ArticleRaw> {
        val fetchedArticles = service.fetchArticles()
        localDataSource.insertArticles(fetchedArticles)
        return fetchedArticles
    }
}