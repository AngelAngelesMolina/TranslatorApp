package com.jaamcoding.translatorapp.transalator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun TranslateView(
    vm: TranslateViewModel = TranslateViewModel(), paddingValues: PaddingValues = PaddingValues()
) {

    val state = vm.state
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val languagesOptions = vm.languageOptions
    val itemSelection = vm.itemSelection

    var indexSource by remember { mutableIntStateOf(0) }
    var indexTarget by remember { mutableIntStateOf(1) }
    var expandedSource by remember { mutableStateOf(false) }
    var expandedTarget by remember { mutableStateOf(false) }

    var selectedSourceLang by remember { mutableStateOf(languagesOptions[0]) }
    var selectedTargetLang by remember { mutableStateOf(languagesOptions[1]) }


    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            DropdownLang(
                itemSelection = itemSelection,
                selectedIndex = indexSource,
                expanded = expandedSource,
                onClickExpand = { expandedSource = true },
                onClickDismiss = { expandedSource = false },
                onClickItem = { index ->
                    indexSource = index
                    selectedSourceLang = languagesOptions[index]
                    expandedSource = false
                })
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                modifier = Modifier.padding(
                    horizontal = 15.dp
                )
            )



            DropdownLang(
                itemSelection = itemSelection,
                selectedIndex = indexTarget,
                expanded = expandedTarget,
                onClickExpand = { expandedTarget = true },
                onClickDismiss = { expandedTarget = false },
                onClickItem = { index ->
                    indexTarget = index
                    selectedTargetLang = languagesOptions[index]
                    expandedTarget = false
                })

        }

        Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(
            value = state.textToTranslate,
            onValueChange = { vm.onValue(it) },
            label = { Text(text = "Escribe el texto a traducir") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                vm.onTranslate(
                    state.textToTranslate,
                    context,
                    selectedSourceLang,
                    selectedTargetLang
                )
            }),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )
        if (state.isDownloading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
                Text(text = "Descargando idioma... Espere un momento ")
            }
        } else {
            OutlinedTextField(
                value = state.translateText,
                onValueChange = {},
                label = { Text(text = "Tu texto traducido") },
                readOnly = false,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
        }

    }


}