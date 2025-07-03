package com.ammar.thmanyah.core.model

import com.google.gson.annotations.SerializedName

data class HomeSectionResponse(
    @SerializedName("sections") val sections: List<Sections>? = null,
    @SerializedName("pagination") val pagination: Pagination? = null,
)