@file:OptIn(ExperimentalMaterial3Api::class)

package com.android.netclandemo.ui.navigation.screens.explorescreen

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.netclandemo.ui.navigation.screens.explorescreen.itemscreen.Businesses
import com.android.netclandemo.ui.navigation.screens.explorescreen.itemscreen.Profile
import com.android.netclandemo.ui.navigation.screens.explorescreen.itemscreen.Service


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun ExplorepageByIndex(currentpage : Int,
                       mainpaddingValue : PaddingValues) {
    var currentstate by remember {
        mutableStateOf(currentpage)
    }
    if (currentstate == 0){
        Profile(
            mainpaddingValue = mainpaddingValue
        )
    }else if (currentstate == 1){
        Service(
            mainpaddingValue = mainpaddingValue
        )
    }else if (currentstate== 2){
        Businesses(
            mainpaddingValue = mainpaddingValue
        )
    }else{
        Profile(
            mainpaddingValue = mainpaddingValue
        )
    }


}

// coustom modifier to fill parent width
fun Modifier.fillParentWidth(): Modifier{
    return this
        .fillMaxWidth()
        .padding(horizontal = 100.dp)

}


@Preview(name = "ExplorepageByIndex",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,

)
@Composable
private fun PreviewExplorepageByIndex() {
    ExplorepageByIndex(
        0,
        PaddingValues(2.dp)
    )
}