package com.android.netclandemo.ui.navigation

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.util.fastForEachIndexed
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.android.netclandemo.ui.navigation.data.Screens

@Composable
fun Bottombar(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val screens = mutableListOf(
        Screens.Home,
        Screens.Home2
    )
    var isselectedindex by rememberSaveable {
        mutableIntStateOf(0)
    }

    BottomAppBar {
        screens.fastForEachIndexed { index, currentitem ->

            NavigationBarItem(
                selected = isselectedindex == index,
                onClick = {
                    isselectedindex = index
                    navController.navigate(currentitem.route){
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(currentitem.icon, contentDescription =null )
                },label = {
                    Text(currentitem.label)
                },
                alwaysShowLabel = false,
            )

        }

    }
}

@Preview(name = "Bottombar")
@Composable
private fun PreviewBottombar() {
    Bottombar(navController =  rememberNavController())
}