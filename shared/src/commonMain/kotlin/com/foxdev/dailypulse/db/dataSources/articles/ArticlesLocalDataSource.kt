package com.foxdev.dailypulse.db.dataSources.articles

import com.foxdev.dailypulse.db.model.ArticleEntity
import com.foxdev.dailypulse.db.model.ArticleSourceEntity
import foxdev.dailypulse.db.DailyPulseDatabase

class ArticlesLocalDataSource(
    private val database: DailyPulseDatabase
) {
    fun getAllArticles(): List<ArticleEntity> =
        database.dailyPulseDatabaseQueries.selectAllArticles(::mapToArticleEntity).executeAsList()

    fun insertArticles(articles: List<ArticleEntity>) {
        database.dailyPulseDatabaseQueries.transaction {
            articles.forEach { articleRaw ->
                insertArticle(articleRaw)
            }
        }
    }

    fun clearArticles() =
        database.dailyPulseDatabaseQueries.removeAllArticles()

    private fun insertArticle(articleEntity: ArticleEntity) {
        database.dailyPulseDatabaseQueries.insertArticle(
            articleEntity.title,
            articleEntity.desc,
            articleEntity.date,
            articleEntity.imageUrl,
            articleEntity.url,
            articleEntity.source.id,
            articleEntity.source.name
        )
    }

    private fun mapToArticleEntity(
        title: String,
        desc: String?,
        date: String,
        imageUrl: String?,
        url: String,
        authorId: String?,
        authorName: String
    ): ArticleEntity =
        ArticleEntity(
            title = title,
            desc = desc,
            date = date,
            imageUrl = imageUrl,
            url = url,
            source = ArticleSourceEntity(
                id = authorId,
                name = authorName
            )
        )
}