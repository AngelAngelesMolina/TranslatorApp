package com.jaamcoding.translatorapp.languages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun LanguagesView() {

    val items = listOf("English", "Spanish")
    val languages = getStrings()
    var currentLanguage by remember { mutableStateOf(languages[0]) }
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = items[selectedIndex])
            IconButton(onClick = { expanded = true }) {
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                items.forEachIndexed { index, item ->
                    DropdownMenuItem(text = { Text(text = item) }, onClick = {
                        selectedIndex = index
                        currentLanguage = languages[index]
                        expanded = false
                    })
                }

            }

        }
        Text(text = currentLanguage["title"].toString(), fontWeight = FontWeight.Bold)
        Text(text = currentLanguage["subtitle"].toString())
    }


}