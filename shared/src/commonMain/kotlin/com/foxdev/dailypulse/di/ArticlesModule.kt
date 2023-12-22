package com.foxdev.dailypulse.di

import com.foxdev.dailypulse.articles.ArticlesService
import com.foxdev.dailypulse.articles.ArticlesUseCase
import com.foxdev.dailypulse.articles.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {
    single<ArticlesService> {
        ArticlesService(get())
    }

    single<ArticlesUseCase> {
        ArticlesUseCase(get())
    }

    single { ArticlesViewModel(get()) }
}