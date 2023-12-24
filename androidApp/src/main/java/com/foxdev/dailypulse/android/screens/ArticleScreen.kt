package com.foxdev.dailypulse.android.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.foxdev.dailypulse.android.Details
import com.foxdev.dailypulse.android.screens.destinations.AboutScreenDestination
import com.foxdev.dailypulse.articles.ArticlesState
import com.foxdev.dailypulse.articles.data.Article
import com.foxdev.dailypulse.articles.ArticlesViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel

@Composable
@Destination(start = true)
fun ArticlesScreen(
    navigator: DestinationsNavigator
) {
    val viewModel: ArticlesViewModel = getViewModel()

    ArticlesScreenContent(
        onAboutButtonClick = {
            navigator.navigate(AboutScreenDestination(Details(id = 5, info = "Details")))
        }, articlesViewModel = viewModel
    )
}

@Composable
fun ArticlesScreenContent(
    onAboutButtonClick: () -> Unit,
    articlesViewModel: ArticlesViewModel,
) {
    val articlesState by articlesViewModel.articlesState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        AppBar(onAboutButtonClick)

        ArticlesContentBox(uiState = articlesState) {
            articlesViewModel.getArticles(true)
        }
    }
}

@Composable
fun ArticlesContentBox(
    modifier: Modifier = Modifier,
    uiState: ArticlesState,
    onRefresh: () -> Unit
) {
    val state = rememberSwipeRefreshState(isRefreshing = uiState.loading)

    SwipeRefresh(
        state = state,
        onRefresh = onRefresh,
        modifier = modifier
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (uiState.error != null)
                ErrorMessage(uiState.error!!)
            if (uiState.articles.isNotEmpty())
                ArticlesListView(uiState.articles)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
    onAboutButtonClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(text = "Articles")
        },
        actions = {
            IconButton(onClick = onAboutButtonClick) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "About Device Button",
                )
            }
        },
    )
}

@Composable
fun ArticlesListView(articles: List<Article>) {

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(articles) { article ->
            ArticleItemView(article = article)
        }
    }
}

@Composable
fun ArticleItemView(article: Article) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = article.imageUrl,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.title,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = article.desc)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.date,
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Composable
fun Loader() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            trackColor = MaterialTheme.colorScheme.secondary,
        )
    }
}

@Composable
fun ErrorMessage(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = TextStyle(fontSize = 28.sp, textAlign = TextAlign.Center)
        )
    }
}