package com.android.netclandemo.ui.navigation.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.sharp.Home
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable


sealed class Screens<T>(
    val route : T,
    val label : String,
    val icon : ImageVector
){

    data object Home : Screens<ExploreScreen>(
        route = ExploreScreen,
        label = "Refine",
        icon = Icons.Sharp.Home
    )

    data object Home2 : Screens<Chat>(
        route = Chat,
        label = "Refine",
        icon = Icons.Filled.Email
    )

}

@Serializable
object  ExploreScreen

@Serializable
object  RefineScreen

@Serializable
object Chat