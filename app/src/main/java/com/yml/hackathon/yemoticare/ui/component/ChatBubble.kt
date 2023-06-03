package com.yml.hackathon.yemoticare.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ChatBubbleMe(
    text: String,
    isError: Boolean = false,
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(start = 34.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Row(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp,
                        bottomEnd = 10.dp,
                    )
                )
                .background(Color.Gray)
                .padding(horizontal = 8.dp)
                .padding(vertical = 4.dp)
        ) {
            Text(
                text = text,
                color = Color.White
            )
            if (isError) {
                Icons.Filled.Warning
            }
        }
    }
}

@Composable
fun ChatBubbleAI(text: String) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(end = 34.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            Icons.Filled.Face
            Text(
                text = text,
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 8.dp,
                            topEnd = 8.dp,
                            bottomEnd = 8.dp,
                        )
                    )
                    .background(Color.DarkGray)
                    .padding(horizontal = 10.dp)
                    .padding(vertical = 4.dp),
                color = Color.White
            )
        }
    }
}

@Composable
fun ChatBubbleTyping() {
    Box(
        Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        TypingTextLoading(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        bottomStart = 8.dp,
                        topEnd = 8.dp,
                        bottomEnd = 8.dp
                    )
                )
                .background(Color.DarkGray)
                .padding(horizontal = 10.dp)
                .padding(vertical = 4.dp),
        )
    }
}
