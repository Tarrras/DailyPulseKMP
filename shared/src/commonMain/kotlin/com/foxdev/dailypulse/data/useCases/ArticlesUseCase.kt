package com.foxdev.dailypulse.data.useCases

import com.foxdev.dailypulse.data.models.ArticleDTO
import com.foxdev.dailypulse.data.repository.ArticlesRepository
import com.foxdev.dailypulse.remote.model.ArticleItemResponse
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.math.abs

class ArticlesUseCase(
    private val repository: ArticlesRepository
) {

    suspend fun getArticles(
        forceFetch: Boolean
    ): List<ArticleDTO> {
        return repository.getArticles(forceFetch).map {
            it.copy(date = getDaysAgoString(it.date))
        }
    }


    private fun getDaysAgoString(date: String): String {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val days = today.daysUntil(
            Instant.parse(date).toLocalDateTime(TimeZone.currentSystemDefault()).date
        )

        val result = when {
            abs(days) > 1 -> "${abs(days)} days ago"
            abs(days) == 1 -> "Yesterday"
            else -> "Today"
        }

        return result
    }
}