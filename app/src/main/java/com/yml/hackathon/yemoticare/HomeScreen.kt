package com.yml.hackathon.yemoticare

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.ThumbUp
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yml.hackathon.yemoticare.ui.component.Pulsating

@Composable
fun HomeScreen(onNavigation: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .padding(top = 30.dp, bottom = 10.dp)
        ) {
            HeartbeatAnimation()
            Text(
                text = "Y-Emoticare",
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
        }
        Text(
            text = "Embrace Your Emotions, Find Strength Within \nYour Emotional Support Companion",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Light,
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AnimatedButton(Icons.Filled.Person, "Talk to your AI friend") {
                onNavigation()
            }
            AnimatedButton(Icons.Rounded.ThumbUp, "Add your daily Check-in") {
            }
        }
    }


}

@Composable
fun AnimatedButton(imageVector: ImageVector, text: String, onClick: () -> Unit) {
    val scale = remember { Animatable(0.8f) }

    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 600)
        )
    }

    Button(
        onClick = onClick,
        modifier = Modifier
            .scale(scale.value)
            .padding(16.dp)
            .fillMaxWidth(),
        contentPadding = PaddingValues(
            26.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFE91E63)
        )
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = text,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Text(text = text)
    }
}

@Composable
fun HeartbeatAnimation() {
    Pulsating {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "Heart",
            tint = Color(0xFFE91E63),
            modifier = Modifier
                .size(80.dp)
                .padding(top = 10.dp)
        )
    }
}



