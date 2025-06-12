package com.example.myweather.utils

fun String.toSentenceCase(): String {
    if (this.isEmpty()) return this
    return this.lowercase().replaceFirstChar {
        if (it.isLowerCase()) it.titlecaseChar() else it
    }
}