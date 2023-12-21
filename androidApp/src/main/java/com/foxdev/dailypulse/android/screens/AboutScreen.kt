package com.foxdev.dailypulse.android.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.foxdev.dailypulse.Platform
import com.foxdev.dailypulse.android.Details

data class AboutScreen(val info: Details) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Log.d("TAG", "Content: ${info.info}")

        AboutScreenContent(
            onUpButtonClicked = {
                navigator.pop()
            }
        )
    }
}

@Composable
fun AboutScreenContent(
    onUpButtonClicked: () -> Unit
) {
    Column {
        Toolbar(onUpButtonClicked)
        ContentView()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar(onUpButtonClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = "About Device")
        }, navigationIcon = {
            IconButton(onClick = onUpButtonClicked) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Up Button")
            }
        }
    )
}

@Composable
private fun ContentView() {
    val items = makeItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(items) {
            RowView(title = it.first, subtitle = it.second)
        }
    }
}

@Composable
fun RowView(
    title: String,
    subtitle: String
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = title, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            Text(text = subtitle, style = MaterialTheme.typography.bodyMedium, color = Color.Black)
        }
        Divider()
    }
}

private fun makeItems(): List<Pair<String, String>> {
    val platform = Platform()
    platform.logSystemInfo()
    return listOf(
        "Operation System" to "${platform.osName} ${platform.osVersion}",
        "Device" to platform.deviceModel,
        "Density" to platform.density.toString(),
    )
}