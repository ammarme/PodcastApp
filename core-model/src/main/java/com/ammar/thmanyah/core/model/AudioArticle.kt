package com.ammar.thmanyah.core.model

import com.google.gson.annotations.SerializedName

data class AudioArticle(
    @SerializedName("article_id")
    val articleId: String?,
    val name: String?,
    @SerializedName("author_name")
    val authorName: String?,
    val description: String?,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    val duration: Int?,
    @SerializedName("release_date")
    val releaseDate: String?,
    val score: Int?
) : Content()
