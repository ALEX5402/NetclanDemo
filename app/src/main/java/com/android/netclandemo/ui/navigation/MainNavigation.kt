package com.android.netclandemo.ui.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.android.netclandemo.ui.navigation.screens.Drawer.DrawerItems
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Mainview(
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background)
    {
        val drawerstate = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val navHostController = rememberNavController()
        ModalNavigationDrawer(
            drawerContent = {
                DrawerItems()
            },
            drawerState = drawerstate,
            modifier = Modifier.fillMaxSize(),

            ) {
            Scaffold(
                bottomBar = {
                    Bottombar(
                        modifier = Modifier,
                        navController = navHostController
                    )
                }
            ) { paddingValues ->
                    Navhost(navHostController,
                        drawerState = {
                            scope.launch {
                                drawerstate.open()
                                Log.w("TAG", "Mainview: ")
                            }
                        },
                        mainpaddingValue = paddingValues
                    )
            }
        }
    }
}

@Preview(name = "Mainview")
@Composable
private fun PreviewMainview() {
    Mainview()
}