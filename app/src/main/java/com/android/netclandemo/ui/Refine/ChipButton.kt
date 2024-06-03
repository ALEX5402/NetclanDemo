package com.android.netclandemo.ui.Refine

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@SuppressLint("UnrememberedMutableState")
@Composable
fun ChipButton(
    modifier: Modifier,
    isSelected : Boolean,
    onclick : () -> Unit,
    label : String

) {
    FilterChip(
        onClick = { onclick() },
        label = {
            Text(label)
        },
        selected = isSelected,
        leadingIcon = if (isSelected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        }, modifier = modifier
    )
}

@Preview(name = "ChipButton")
@Composable
private fun PreviewChipButton() {
ChipButton(isSelected = true, onclick = { /*TODO*/ }, label = "test", modifier = Modifier)
}