package com.foxdev.dailypulse.data.models

import com.foxdev.dailypulse.db.model.ArticleEntity
import com.foxdev.dailypulse.db.model.ArticleSourceEntity
import com.foxdev.dailypulse.remote.model.ArticleItemResponse
import com.foxdev.dailypulse.remote.model.ArticleSourceResponse


fun ArticleItemResponse.toDomainModel(): ArticleDTO {
    return ArticleDTO(
        title = title,
        desc = desc ?: "Click to find out more",
        date = date,
        imageUrl = imageUrl
            ?: "https://image.cnbcfm.com/api/v1/image/107326078-1698758530118-gettyimages-1765623456-wall26362_igj6ehhp.jpeg?v=1698758587&w=1920&h=1080",
        url = url,
        source = source.toDomainModel()
    )
}

fun ArticleSourceResponse.toDomainModel() = ArticleSource(
    id, name
)

fun ArticleEntity.toDomainModel(): ArticleDTO {
    return ArticleDTO(
        title = title,
        desc = desc ?: "Click to find out more",
        date = date,
        imageUrl = imageUrl
            ?: "https://image.cnbcfm.com/api/v1/image/107326078-1698758530118-gettyimages-1765623456-wall26362_igj6ehhp.jpeg?v=1698758587&w=1920&h=1080",
        url = url,
        source = source.toDomainModel()
    )
}

fun ArticleSourceEntity.toDomainModel() = ArticleSource(
    id, name
)

fun ArticleDTO.toEntityModel(): ArticleEntity {
    return ArticleEntity(
        title = title,
        desc = desc,
        date = date,
        imageUrl = imageUrl,
        url = url,
        source = source.toEntityModel()
    )
}

fun ArticleSource.toEntityModel() = ArticleSourceEntity(
    id, name
)