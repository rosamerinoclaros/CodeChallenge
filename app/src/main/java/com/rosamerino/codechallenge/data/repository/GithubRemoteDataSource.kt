package com.rosamerino.codechallenge.data.repository

import com.rosamerino.codechallenge.api.GitHubService
import com.rosamerino.codechallenge.data.model.Repo
import com.rosamerino.codechallenge.data.model.RepoItems
import com.rosamerino.codechallenge.util.Result
import retrofit2.Retrofit
import javax.inject.Inject


class GithubRemoteDataSource @Inject constructor(private val retrofit: Retrofit) {
    suspend fun fetchGithubRepos(query: String): Result<RepoItems?> {
        val githubService = retrofit.create(GitHubService::class.java)
        return try {
            val result = githubService.listRepos(query)
            if (result.isSuccessful) Result.success(data = githubService.listRepos(query).body())
            // TODO: parse error body to user friendly error message
            else Result.error(data = null, message = result.errorBody().toString())
        } catch (exception: Exception) {
            Result.error(data = null, message = exception.message ?: "Error Occurred!")
        }
    }
}

