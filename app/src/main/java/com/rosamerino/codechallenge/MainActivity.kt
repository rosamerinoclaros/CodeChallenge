package com.rosamerino.codechallenge

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.rosamerino.codechallenge.data.model.RepoItems
import com.rosamerino.codechallenge.search.SearchView
import com.rosamerino.codechallenge.search.SearchViewModel
import com.rosamerino.codechallenge.ui.common.CodeChallengeTopBar
import com.rosamerino.codechallenge.ui.theme.CodeChallengeTheme
import com.rosamerino.codechallenge.util.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val searchViewModel: SearchViewModel by viewModels()
        setContent {
            CodeChallengeTheme {
                Scaffold(
                    topBar = { CodeChallengeTopBar() }
                ) {
                    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                        val keyboardController = LocalSoftwareKeyboardController.current
                        var searchQuery by remember { mutableStateOf(searchViewModel.searchQuery.value ?: "") }
                        Column {
                            TextField(
                                value = searchQuery,
                                onValueChange = {
                                    searchQuery = it
                                    searchViewModel.onSearchQueryChange(it)
                                },
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth(),
                                placeholder = {
                                    Text(
                                        "Write any keyword to search github repositories",
                                        color = Color.Gray
                                    )
                                },
                                maxLines = 1,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Done
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        keyboardController?.hide()
                                        searchViewModel.fetchRepos()
                                    }
                                )
                            )

                            Spacer(modifier = Modifier.size(16.dp))

                            val repoItems: Result<RepoItems?> by searchViewModel.repoList.observeAsState(initial = Result.success(null))
                            SearchView(repoItems)
                        }
                    }
                }
            }
        }
    }
}
