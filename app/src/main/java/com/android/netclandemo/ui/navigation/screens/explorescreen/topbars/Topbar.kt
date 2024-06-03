package com.android.netclandemo.ui.navigation.screens.explorescreen.topbars

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreTopbar(
    navigationIconOnclick: () -> Unit,
    actionIconOnclick: () -> Unit,
) {
    TopAppBar(
        title = {
            Row {
               Spacer(modifier = Modifier.width(20.dp))
                Column(
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(text = "Welcome User" ,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.W500,
                        textAlign = TextAlign.Center ,
                        modifier = Modifier.align(
                            Alignment.CenterHorizontally
                        )
                    )
                    Row {
                        Icon(
                            Icons.Filled.LocationOn,
                            contentDescription =null,
                            modifier = Modifier
                                .size(
                                    width = 10.dp,
                                    height = 10.dp
                                )
                                .align(
                                    Alignment.CenterVertically
                                )

                        )
                        Text(text = "Loading..." ,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(2.dp)
                        )
                    }
                }
            }

    }, navigationIcon = {
        Row{
            Icon(Icons.Filled.Menu,
                contentDescription =null,
                modifier = Modifier
                    .size(40.dp)
                    .clickable(
                        onClick = {
                            navigationIconOnclick()
                        },
                        role = Role.Button
                    ))
        }
    }, actions = {
        Column(
            modifier = Modifier.clickable(
                onClick = {
                    actionIconOnclick()
                },
                role = Role.Button
            )
        ) {
            Icon(Icons.Filled.List ,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(text = "Refine")
        }
    })

}
@Preview(name = "Topbar")
@Composable
private fun PreviewTopbar() {
    ExploreTopbar({},{},)
}