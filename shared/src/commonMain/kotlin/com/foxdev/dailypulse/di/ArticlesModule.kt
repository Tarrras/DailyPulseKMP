package com.foxdev.dailypulse.di

import com.foxdev.dailypulse.articles.remote.ArticlesService
import com.foxdev.dailypulse.articles.ArticlesUseCase
import com.foxdev.dailypulse.articles.ArticlesViewModel
import com.foxdev.dailypulse.articles.data.ArticlesDataSource
import com.foxdev.dailypulse.articles.data.ArticlesRepository
import org.koin.dsl.module

val articlesModule = module {
    single<ArticlesService> {
        ArticlesService(get())
    }

    single<ArticlesUseCase> {
        ArticlesUseCase(get())
    }

    single { ArticlesViewModel(get()) }
    single { ArticlesDataSource(get()) }
    single { ArticlesRepository(get(), get()) }
}