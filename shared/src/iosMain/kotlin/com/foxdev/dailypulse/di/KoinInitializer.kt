package com.foxdev.dailypulse.di

import com.foxdev.dailypulse.viewModels.articles.ArticlesViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {
    val modules = sharedModules + databaseModule

    startKoin {
        modules(modules)
    }
}


class ArticlesInjector : KoinComponent {
    val articlesViewModel: ArticlesViewModel by inject()
}