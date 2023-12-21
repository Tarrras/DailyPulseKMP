package com.foxdev.dailypulse.android

import android.os.Parcelable
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import cafe.adriel.voyager.transitions.SlideTransition
import com.foxdev.dailypulse.android.screens.ArticleScreen
import com.foxdev.dailypulse.articles.ArticlesViewModel
import kotlinx.parcelize.Parcelize

@Composable
fun AppScaffold() {

    Scaffold { paddingValues ->
        Navigator(ArticleScreen) { navigator ->
            FadeTransition(navigator)
        }
    }

}


@Parcelize
data class Details(
    val id: Int,
    val info: String
) : Parcelable