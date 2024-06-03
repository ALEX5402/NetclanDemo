package com.android.netclandemo.ui.Refine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import com.android.netclandemo.ui.theme.NetclandemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RefineActivity : ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NetclandemoTheme {
                Surface {
                 RefineScreen(onClickBack = {
                     onBackPressed()
                 } )
                }
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}