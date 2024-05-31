package com.android.netclandemo.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.netclandemo.ui.navigation.data.Chat
import com.android.netclandemo.ui.navigation.data.ExploreScreen
import com.android.netclandemo.ui.navigation.data.RefineScreen
import com.android.netclandemo.ui.navigation.data.Screens
import com.android.netclandemo.ui.navigation.screens.explorescreen.ExploreScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Navhost(
    navHostController : NavHostController,
    drawerState: () -> Unit,
    mainpaddingValue : PaddingValues
) {
    NavHost(navController = navHostController,
        startDestination = Screens.Home.route) {
        composable<ExploreScreen>{
            ExploreScreen(
                navHostController = navHostController,
                drawerState = {
                    drawerState()
                },
                mainpaddingValue = mainpaddingValue
            )
        }
        composable<RefineScreen> {
            
        }
        composable<Chat>{
            Box(Modifier.fillMaxSize()) {
                Text(text = "i am Screen B " , modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

