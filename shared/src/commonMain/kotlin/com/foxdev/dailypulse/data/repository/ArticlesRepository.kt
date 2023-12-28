package com.foxdev.dailypulse.data.repository

import com.foxdev.dailypulse.data.models.ArticleDTO
import com.foxdev.dailypulse.data.models.toDomainModel
import com.foxdev.dailypulse.data.models.toEntityModel
import com.foxdev.dailypulse.db.dataSources.articles.ArticlesLocalDataSource
import com.foxdev.dailypulse.remote.model.ArticleItemResponse
import com.foxdev.dailypulse.remote.ArticlesService

class ArticlesRepository(
    private val localDataSource: ArticlesLocalDataSource,
    private val remoteDataSourceService: ArticlesService
) {

    suspend fun getArticles(forceFetch: Boolean): List<ArticleDTO> {
        if (forceFetch) {
            localDataSource.clearArticles()
            return fetchAndUpdateLocalArticles()
        }

        val localArticles = localDataSource.getAllArticles().map { it.toDomainModel() }

        if (localArticles.isEmpty()) {
            return fetchAndUpdateLocalArticles()
        }

        return localArticles
    }

    private suspend fun fetchAndUpdateLocalArticles(): List<ArticleDTO> {
        val fetchedArticles =
            remoteDataSourceService.fetchTopHeadlineArticles().map { it.toDomainModel() }
        localDataSource.insertArticles(fetchedArticles.map { it.toEntityModel() })
        return fetchedArticles
    }
}