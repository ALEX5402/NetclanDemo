package com.android.netclandemo.ui.navigation.screens.Drawer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DrawerItems(

) {
    ModalDrawerSheet {
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(name = "DrawerItems")
@Composable
private fun PreviewDrawerItems() {
    DrawerItems()
}