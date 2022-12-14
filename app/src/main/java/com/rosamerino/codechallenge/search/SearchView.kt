package com.rosamerino.codechallenge.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rosamerino.codechallenge.data.model.Repo
import com.rosamerino.codechallenge.data.model.RepoItems
import com.rosamerino.codechallenge.ui.common.EmptyView
import com.rosamerino.codechallenge.ui.common.ErrorView
import com.rosamerino.codechallenge.ui.common.LoadingView
import com.rosamerino.codechallenge.util.Result
import com.rosamerino.codechallenge.util.State
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SearchView(repoItems: Result<RepoItems?>) {
    when (repoItems.state) {
        State.LOADING -> LoadingView()
        State.SUCCESS -> {
            if (repoItems.data?.items.isNullOrEmpty()) EmptyView()
            else repoItems.data?.let { RepoListView(it.items) }
        }
        State.ERROR -> repoItems.message?.let { ErrorView(it) }
    }
}

@Composable
fun RepoListView(
    repoItemList: List<Repo>
) {
    if (repoItemList.isEmpty())
        EmptyView()
    else {
        LazyColumn {
            items(repoItemList.size) { item ->
                RepoItemRow(repoItemList[item])
                Divider()
            }
        }
    }

    Spacer(modifier = Modifier.size(20.dp))
}

@Composable
fun RepoItemRow(repoItem: Repo) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)

    ) {
        GlideImage(
            imageModel = { repoItem.owner.avatarUrl },
            modifier = Modifier.size(50.dp)
        )

        Column(Modifier.padding(start = 32.dp)) {
            Text(
                text = repoItem.name,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = repoItem.title,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            if (repoItem.description != null) {
                Text(
                    text = repoItem.description,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            Text(
                text = repoItem.owner.name,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = repoItem.url,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}
