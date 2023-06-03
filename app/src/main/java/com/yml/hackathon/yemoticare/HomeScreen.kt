package com.yml.hackathon.yemoticare

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.yml.hackathon.yemoticare.ui.component.Pulsating

@Composable
fun HomeScreen(onNavigation: (String) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 30.dp, bottom = 10.dp)
        ) {
            HeartbeatAnimation()
            Text(
                text = "Y-EmotiCare",
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
        }
        Text(
            text = "Embrace Your Emotions, Find Strength Within \nYour Emotional Support Companion",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Light
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedButton(Icons.Filled.Person, "Talk to your AI friend") {
                onNavigation("chat")
            }
            AnimatedButton(Icons.Rounded.ThumbUp, "Add your daily Check-in") {
                onNavigation("checkin")
            }
        }
    }
}

@Composable
fun AnimatedButton(imageVector: ImageVector, text: String, onClick: () -> Unit) {
    val scale = remember { Animatable(0.8f) }

    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f, animationSpec = tween(durationMillis = 600)
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
fun CheckInScreen(onDismiss: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AlertDialog(onDismissRequest = { onDismiss() }, title = {
            Text(
                text = "Check-In",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.fillMaxWidth()
            )
        }, text = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                QuestionText(text = "How would you describe your mood today?")
                ImageRow()
                QuestionText(text = "Did you get enough sleep?")
                Options()
                QuestionText(text = "Have you been feeling generally positive today?")
                Options()
                QuestionText(text = "Have you noticed any significant changes in your mood since the last check-in?")
                Options()
                QuestionText(text = "Have you engaged in any activities that bring you joy or relaxation today?")
                Options()
                QuestionText(text = "Have you experienced any thoughts of self-harm or suicide today?")
                Options()
            }
        }, confirmButton = {
            Button(
                onClick = { onDismiss() }, colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE64B4B)
                )
            ) {
                Text("Dismiss")
            }
        }, properties = DialogProperties(
            dismissOnBackPress = false, dismissOnClickOutside = false
        )
        )
    }
}

@Composable
private fun Options() {
    var selectedOption by remember { mutableStateOf("") }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selectedOption == "Yes", onClick = { selectedOption = "Yes" })
        Text("Yes", modifier = Modifier.padding(start = 0.dp))

        RadioButton(selected = selectedOption == "No", onClick = { selectedOption = "No" })
        Text("No")
    }
}

@Composable
fun ImageRow() {
    var selectedIndex by remember { mutableStateOf<Int?>(null) }

    Row(modifier = Modifier.fillMaxWidth()) {
        Image(painter = if (selectedIndex == 0) {
            painterResource(id = R.drawable.grinning_face_with_smiling_eyes_filled)
        } else {
            painterResource(id = R.drawable.grinning_face_with_smiling_eyes_unfilled)
        },
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .clickable { selectedIndex = 0 })
        Spacer(modifier = Modifier.width(8.dp))
        Image(painter = if (selectedIndex == 1) {
            painterResource(id = R.drawable.grinning_face_filled)
        } else {
            painterResource(id = R.drawable.grinning_face_unfilled)
        },
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .clickable { selectedIndex = 1 })
        Spacer(modifier = Modifier.width(8.dp))
        Image(painter = if (selectedIndex == 2) {
            painterResource(id = R.drawable.frowning_face_filled)
        } else {
            painterResource(id = R.drawable.frowning_face_unfilled)
        },
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .clickable { selectedIndex = 2 })
        Spacer(modifier = Modifier.width(8.dp))
        Image(painter = if (selectedIndex == 3) {
            painterResource(id = R.drawable.angry_face_filled)
        } else {
            painterResource(id = R.drawable.angry_face_unfilled)
        },
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .clickable { selectedIndex = 3 })
    }
}

@Composable
fun QuestionText(text: String) {
    Text(
        text = text, textAlign = TextAlign.Center, fontSize = 14.sp, fontFamily = FontFamily.Serif
    )
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
