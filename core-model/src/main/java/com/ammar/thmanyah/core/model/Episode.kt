package com.ammar.thmanyah.core.model

data class Episode(
    val episode_id: String?,
    val name: String?,
    val season_number: Int?,
    val episode_type: String?,
    val podcast_name: String?,
    val author_name: String?,
    val description: String?,
    val duration: Int?,
    val audio_url: String?,
    val avatar_url: String?,
    val releaseDate: String?,
    val podcast_id: String?,
    val score: Double?
) : Content()
