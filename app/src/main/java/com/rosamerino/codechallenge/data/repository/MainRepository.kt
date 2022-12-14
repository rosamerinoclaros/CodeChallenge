package com.rosamerino.codechallenge.data.repository

import com.rosamerino.codechallenge.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor (private val githubRemoteDataSource: GithubRemoteDataSource) {
    suspend fun getRepos(query: String) = flow {
        emit(Result.loading(null))
        emit(githubRemoteDataSource.fetchGithubRepos(query))
    }.flowOn(Dispatchers.IO)
}
