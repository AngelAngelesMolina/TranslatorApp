package com.jaamcoding.translatorapp.transalator

data class TranslateState(
    val textToTranslate: String = "",
    val translateText: String = "",
    val isDownloading: Boolean = false
)
