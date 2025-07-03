package com.ammar.thmanyah.core.network.mapper

import com.ammar.thmanyah.core.model.AudioArticle
import com.ammar.thmanyah.core.model.AudioBook
import com.ammar.thmanyah.core.model.Content
import com.ammar.thmanyah.core.model.Episode
import com.ammar.thmanyah.core.model.Podcast
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class ContentTypBuilder : JsonDeserializer<Content> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Content {
        val jsonObject = json.asJsonObject

        // Try to determine type from existing ID fields (fallback for existing API)
        val contentType = when {
            jsonObject.has("episode_id") -> "episode"
            jsonObject.has("audiobook_id") -> "audiobook"
            jsonObject.has("article_id") -> "article"
            jsonObject.has("podcast_id") -> "podcast"
            else -> jsonObject.get("type")?.asString
        }

        return when (contentType) {
            "episode" -> context.deserialize(json, Episode::class.java)
            "audiobook" -> context.deserialize(json, AudioBook::class.java)
            "article" -> context.deserialize(json, AudioArticle::class.java)
            "podcast" -> context.deserialize(json, Podcast::class.java)
            else -> throw JsonParseException("Unknown content type: $contentType")
        }
    }
}