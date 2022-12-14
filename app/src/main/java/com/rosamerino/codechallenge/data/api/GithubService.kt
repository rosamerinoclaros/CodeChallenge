package com.rosamerino.codechallenge.api

import com.rosamerino.codechallenge.data.model.Repo
import com.rosamerino.codechallenge.data.model.RepoItems
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {
    @GET("/search/repositories")
    suspend fun listRepos(@Query("q") query: String?): Response<RepoItems>
}
