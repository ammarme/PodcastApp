package com.ammar.thmanyah.core.model

import com.google.gson.annotations.SerializedName

data class Podcast(
    @SerializedName("podcast_id") val podcastId: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("avatar_url") val avatarUrl: String? = null,
    @SerializedName("episode_count") val episodeCount: Int? = null,
    @SerializedName("duration") val duration: Int? = null,
    @SerializedName("language") val language: String? = null,
    @SerializedName("priority") val priority: String? = null,
    @SerializedName("popularityScore") val popularityScore: String? = null,
    @SerializedName("score") val score: String? = null

) : Content()