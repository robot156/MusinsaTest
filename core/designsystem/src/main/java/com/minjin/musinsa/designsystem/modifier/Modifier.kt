package com.minjin.musinsa.designsystem.modifier

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun Modifier.onClick(
    onClick: () -> Unit,
): Modifier {
    return this.clickable(
        onClick = onClick,
        interactionSource = remember { MutableInteractionSource() },
        indication = ripple()
    )
}