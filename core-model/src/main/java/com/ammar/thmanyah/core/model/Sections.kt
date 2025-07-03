package com.ammar.thmanyah.core.model

import com.google.gson.annotations.SerializedName

data class Sections(
    @SerializedName("name") val name: String? = null,
    @SerializedName("type") val type: String? = null,
    @SerializedName("content_type") val contentType: String? = null,
    @SerializedName("order") val order: String? = null,
    @SerializedName("content") val content: List<Content>? = null

)