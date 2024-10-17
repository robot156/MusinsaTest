package com.minjin.musinsa.designsystem.image

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.minjin.musinsa.designsystem.R

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    imageUrl: String? = "",
    contentDescription: String = "",
    placeholder: Painter? = painterResource(R.drawable.icon_loading),
    contentScale: ContentScale = ContentScale.Crop,
) {
    if (LocalInspectionMode.current) {
        Icon(
            modifier = modifier,
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Example Image Icon",
        )
    } else {
        AsyncImage(
            modifier = modifier,
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(imageUrl)
                .build(),
            placeholder = placeholder,
            contentDescription = contentDescription,
            contentScale = contentScale,
        )
    }
}

@Preview
@Composable
private fun NetworkImagePreview() {
    MaterialTheme {
        NetworkImage(
            imageUrl = "",
            contentDescription = "",
        )
    }
}