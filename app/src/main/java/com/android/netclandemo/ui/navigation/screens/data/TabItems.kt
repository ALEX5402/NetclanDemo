package com.android.netclandemo.ui.navigation.screens.data

sealed class TabItems(
    var title: String,
){
   data object Personal : TabItems(
        title = "Personal",
    )
 data object Service : TabItems(
        title = "Service",
    )
 data object Businesses : TabItems(
        title = "Businesses",
    )
}