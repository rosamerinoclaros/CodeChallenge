package com.rosamerino.codechallenge.data.model

import com.google.gson.annotations.SerializedName

data class RepoItems(
    @SerializedName("items")
    val items: List<Repo>
)

data class Repo(
    @SerializedName("full_name")
    val name: String,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("name")
    val title: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("url")
    val url: String
)

data class Owner(
    @SerializedName("login")
    val name: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)
