package com.supdevinci.neuronboost.utils

import androidx.core.text.HtmlCompat

fun decodeHtml(text: String): String {
    return HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
}