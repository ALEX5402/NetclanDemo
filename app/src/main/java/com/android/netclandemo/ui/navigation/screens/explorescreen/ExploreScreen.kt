package com.android.netclandemo.ui.navigation.screens.explorescreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.android.netclandemo.ui.navigation.screens.data.TabItems
import com.android.netclandemo.ui.navigation.screens.topbars.ExploreTopbar
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ExploreScreen(
    navHostController: NavHostController,
    drawerState : () -> Unit,
    mainpaddingValue : PaddingValues,
    exploreViewmodel: ExploreViewmodel = hiltViewModel()
) {
    val tabitems = listOf(
        TabItems.Personal,
        TabItems.Service,
        TabItems.Businesses
    )

    var selectedtabstate by remember {
        mutableIntStateOf(0)
    }
    val pagestate = rememberPagerState {
        tabitems.size
    }

    LaunchedEffect(
        key1 = pagestate.currentPage
    ) {
        selectedtabstate = pagestate.currentPage
    }
    LaunchedEffect(
        key1 = selectedtabstate
    ) {
        val Thepage = selectedtabstate == pagestate.currentPage
        if (!Thepage) {
            pagestate.animateScrollToPage(page = selectedtabstate)
        }


    }
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            ExploreTopbar(
                actionIconOnclick = { },
                navigationIconOnclick = { drawerState() })
        }
    ) { innerpaddinf ->
        Scaffold(
            modifier = Modifier.padding(innerpaddinf),
            topBar = {
                TabRow(
                    selectedTabIndex = selectedtabstate,
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = Color.White,
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            Modifier.tabIndicatorOffset(tabPositions[pagestate.currentPage]),
                            height = 2.dp,
                            color = Color.White
                        )
                    }
                ) {

                    tabitems.forEachIndexed { index, tabItems ->
                        Tab(
                            selected = pagestate.currentPage == index,
                            onClick = {
                                scope.launch {
                                    pagestate.animateScrollToPage(page = index)
                                }
                            },
                            text = {
                                Text(
                                    text = tabItems.title,
                                    color = if (index == pagestate.currentPage) Color.White else Color.Gray
                                )
                            },
                        )
                    }

                }
            }
        ) {paddingValues ->
            Box(
                modifier = Modifier.padding(mainpaddingValue),
            ) {
                HorizontalPager(
                    state = pagestate,
                    modifier = Modifier
                        .fillMaxSize()
                ) { currentpageindex->
                    ExplorepageByIndex(
                        currentpage = currentpageindex,
                        tabitems = tabitems[currentpageindex].title)
                }
            }

        }
    }
}

@Preview
@Composable
fun Preview(modifier: Modifier = Modifier) {
    ExploreScreen(
        navHostController = rememberNavController(),
        drawerState = {},
        mainpaddingValue = PaddingValues(10.dp)
    )
}

