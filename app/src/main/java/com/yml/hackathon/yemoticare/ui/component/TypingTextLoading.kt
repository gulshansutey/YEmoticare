package com.yml.hackathon.yemoticare.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay

private const val TYPING_INTERVAL = 250L

@Composable
fun TypingTextLoading(modifier: Modifier = Modifier) {
    val typingProgressOne = "."
    val typingProgressTwo = ".."
    val typingProgressThree = "..."
    var textToDisplay by remember {
        mutableStateOf(typingProgressOne)
    }
    Text(
        modifier = modifier, text = textToDisplay, color = Color.Red
    )
    LaunchedEffect(Unit) {
        while (true) {
            textToDisplay = when (textToDisplay) {
                typingProgressThree -> typingProgressOne
                typingProgressOne -> typingProgressTwo
                else -> typingProgressThree
            }
            delay(TYPING_INTERVAL)
        }
    }
}
