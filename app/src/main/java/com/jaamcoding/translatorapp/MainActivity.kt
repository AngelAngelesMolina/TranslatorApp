package com.jaamcoding.translatorapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jaamcoding.translatorapp.languages.LanguagesView
import com.jaamcoding.translatorapp.transalator.TranslateView
import com.jaamcoding.translatorapp.transalator.TranslateViewModel
import com.jaamcoding.translatorapp.ui.theme.TranslatorAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm : TranslateViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            TranslatorAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    TranslateView(vm)
                }
            }
        }
    }
}
