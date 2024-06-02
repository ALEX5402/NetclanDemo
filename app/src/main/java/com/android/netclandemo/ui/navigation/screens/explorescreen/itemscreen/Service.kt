package com.android.netclandemo.ui.navigation.screens.explorescreen.itemscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Service(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "Service")
    }
}

@Preview(name = "Service")
@Composable
private fun PreviewService() {
    Service()
}