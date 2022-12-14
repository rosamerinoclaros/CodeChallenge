package com.rosamerino.codechallenge.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CodeChallengeTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Coding Challenge",
                style = MaterialTheme.typography.h5
            )
        },
        backgroundColor = MaterialTheme.colors.primaryVariant,
    )
}

@Composable
fun ErrorView(message: String = "An error occurred") {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = Icons.Default.Warning,
            contentDescription = "",
            colorFilter = ColorFilter.tint(Color.Red),
            modifier = Modifier.size(108.dp)
        )
        Spacer(Modifier.size(12.dp))
        ErrorText(message)
    }
}

@Composable
fun LoadingView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Column(
            modifier = Modifier
                .size(60.dp)
                .background(MaterialTheme.colors.background)
        ) {
            CircularProgressIndicator(color = Color.Black)
        }
    }
}

@Composable
fun EmptyView(message: String = "Nothing here!") {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = Icons.Default.Warning,
            contentDescription = "",
            colorFilter = ColorFilter.tint(Color.Black.copy(alpha = 0.8F)),
            modifier = Modifier.size(108.dp)
        )
        Spacer(Modifier.size(12.dp))
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            EmptyText(message)
        }
    }
}

@Composable
fun ErrorText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp),
        textAlign = TextAlign.Center, color = Color.Red
    )
}

@Composable
fun EmptyText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp),
        lineHeight = 20.sp,
        textAlign = TextAlign.Center
    )
}
