package com.jaamcoding.translatorapp.transalator

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions

class TranslateViewModel : ViewModel() {
    val languageOptions = listOf(
        TranslateLanguage.SPANISH,
        TranslateLanguage.ENGLISH,
        TranslateLanguage.ITALIAN,
        TranslateLanguage.FRENCH
    )
    val itemSelection = listOf(
        "Spanish",
        "English",
        "Italian",
        "French"
    )

    var state by mutableStateOf(TranslateState())
        private set


    fun onValue(text: String) {
        state = state.copy(textToTranslate = text)
    }

    fun onTranslate(
        text: String, context: Context, sourceLang: String, targetLang: String
    ) {
        val options = TranslatorOptions
            .Builder().setSourceLanguage(sourceLang)
            .setTargetLanguage(targetLang)
            .build()

        val languageTranslator = Translation.getClient(options)
        languageTranslator.translate(text)
            .addOnSuccessListener { translateText -> //dar el valor traducido
                state = state.copy(translateText = translateText)
            }
            .addOnFailureListener { exception ->
                Toast.makeText(context, "Descargando idioma...", Toast.LENGTH_SHORT).show()
                downloadingModel(languageTranslator, context)
            }
    }

    private fun downloadingModel(languageTranslator: Translator, context: Context) {
        state = state.copy(isDownloading = true)
        val conditions = DownloadConditions.Builder().requireWifi().build()
        languageTranslator.downloadModelIfNeeded(conditions).addOnSuccessListener {
            Toast.makeText(context, "Idioma descargado correctamente", Toast.LENGTH_SHORT).show()
            state = state.copy(isDownloading = false)
//            onTranslate(
//                state.textToTranslate,
//                context,
//                TranslateLanguage.ENGLISH,
//                TranslateLanguage.SPANISH
//            )
        }.addOnFailureListener {
            state = state.copy(isDownloading = false)
        }

    }


}