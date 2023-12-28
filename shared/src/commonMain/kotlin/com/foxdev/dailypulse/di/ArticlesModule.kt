package com.foxdev.dailypulse.di

import com.foxdev.dailypulse.remote.ArticlesService
import com.foxdev.dailypulse.data.useCases.ArticlesUseCase
import com.foxdev.dailypulse.viewModels.articles.ArticlesViewModel
import com.foxdev.dailypulse.db.dataSources.articles.ArticlesLocalDataSource
import com.foxdev.dailypulse.data.repository.ArticlesRepository
import org.koin.dsl.module

val articlesModule = module {
    single<ArticlesService> {
        ArticlesService(get())
    }

    single<ArticlesUseCase> {
        ArticlesUseCase(get())
    }

    single { ArticlesViewModel(get()) }
    single { ArticlesLocalDataSource(get()) }
    single { ArticlesRepository(get(), get()) }
}