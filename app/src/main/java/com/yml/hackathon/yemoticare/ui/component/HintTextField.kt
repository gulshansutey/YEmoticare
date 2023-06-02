package com.yml.hackathon.yemoticare.ui.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private const val ENABLED_OPACITY = 1F
private const val DISABLED_OPACITY = 0.4F

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HintTextField(
    value: String,
    modifier: Modifier = Modifier,
    hint: String = "",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    maxLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onTextChanged: (String) -> Unit = {},
) {
    TextField(
        value = value,
        onValueChange = { onTextChanged(it) },
        modifier = modifier.alpha(if (enabled) ENABLED_OPACITY else DISABLED_OPACITY),
        enabled = enabled,
        readOnly = readOnly,
        keyboardOptions = keyboardOptions,
        maxLines = maxLines,
        shape = RoundedCornerShape(8.dp),
        placeholder = {
            Text(
                text = hint,
                color = Color.Black
            )
        }
    )
}
