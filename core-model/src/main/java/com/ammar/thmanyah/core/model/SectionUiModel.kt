package com.ammar.thmanyah.core.model

import android.annotation.SuppressLint

data class SectionUiModel(
    val title: String,
    val type: ItemType,
    val content: List<ItemUiModel>,
    val order: Int,
)

enum class ItemType(val type: String) {
    Queue("queue"),
    Square("square"),
    BigSquare("big_square"),
    TwoLinesGrid("2_lines_grid");

    companion object {
        @SuppressLint("SuspiciousIndentation")
        fun fromString(type: String): ItemType? {
            return try {
                if (type == "big square")
                    return BigSquare
                else
                    entries.firstOrNull { it.type == type }
            } catch (_: IllegalArgumentException) {
                return Square
            }
        }
    }
}