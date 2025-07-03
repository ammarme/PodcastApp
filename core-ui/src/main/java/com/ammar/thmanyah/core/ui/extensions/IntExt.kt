package com.ammar.thmanyah.core.ui.extensions

fun Int.fromMinsToHoursMinFormat(): String {
    val hours = (this / 3600)
    val minutes = ((this % 3600) / 60)

    return "$hours:$minutes"
}