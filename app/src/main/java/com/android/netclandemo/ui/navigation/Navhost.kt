package com.android.netclandemo.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.netclandemo.ui.navigation.data.ExploreScreen
import com.android.netclandemo.ui.navigation.data.RefineScreen
import com.android.netclandemo.ui.navigation.data.Screen2
import com.android.netclandemo.ui.navigation.data.Screens
import com.android.netclandemo.ui.navigation.screens.explorescreen.ExploreScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun  Navhost( // with bottombars
    navHostController : NavHostController,
    drawerState: () -> Unit,
    mainpaddingValue : PaddingValues
) {
    NavHost(navController = navHostController,
        startDestination = Screens.Explore.route) {
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
            Text(text = "hi hi"
                ,
                Modifier
                    .fillMaxSize()
                    .padding(90.dp)
            )
        }
        composable<Screen2> {
            Text(text = "Screen 2"
                ,
                Modifier
                    .fillMaxSize()
                    .padding(90.dp)
            )
        }
    }
}





