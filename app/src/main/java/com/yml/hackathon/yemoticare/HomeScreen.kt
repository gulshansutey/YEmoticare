package com.yml.hackathon.yemoticare

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
    Column {
        AppBar(title = "Yemoticare")
        HomeScreenButtons()
    }
}

@Composable
fun HomeScreenButtons() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedButton(Icons.Filled.Person, "Talk to your AI friend")
        AnimatedButton(Icons.Filled.Check, "Add your daily Check-in")
    }
}

@Composable
fun AppBar(title: String) {
    Text(
        text = title,
        color = Color.White,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .background(color = Color.DarkGray)
            .padding(16.dp)
            .fillMaxWidth()
    )
}

@Composable
fun AnimatedButton(imageVector: ImageVector, text: String) {
    val scale = remember { Animatable(1f) }

    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1.1f,
            animationSpec = tween(durationMillis = 1000)
        )
    }

    Button(
        onClick = { /* Button click handler */ },
        modifier = Modifier
            .scale(scale.value)
            .padding(16.dp)
            .fillMaxWidth(),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 16.dp
        ),
        border = BorderStroke(width = 2.dp, Color.LightGray),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFE91E63)
        )
    ) {
        Icon(imageVector = imageVector, contentDescription = text)
        Text(text = text)
    }
}
