package com.android.netclandemo.ui.navigation.screens.Drawer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DrawerItems() {
    val items = listOf(
        "Homescreen",
        "Example Screen",
        "Example Screen2",
        "Example Screen3",
    )
    ModalDrawerSheet(
        modifier = Modifier.padding(
            end = 30.dp
        )
    ) {
        Spacer(modifier = Modifier
            .height(16.dp)
        )
        Text("Drawer title", modifier = Modifier.padding(16.dp))
        Divider()
        NavigationDrawerItem(
            label = { Text(text = "Enabled Item Demo") },
            selected = true,
            onClick = {

            }
        )
        Divider()
        items.forEach { items->
            NavigationDrawerItem(
                label = { Text(text = items) },
                selected = false,
                onClick = {

                }
            )
            Divider()
        }



    }
}

@Preview(name = "DrawerItems")
@Composable
private fun PreviewDrawerItems() {
    DrawerItems()
}