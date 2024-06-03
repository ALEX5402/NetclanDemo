package com.android.netclandemo.ui.navigation.screens.explorescreen.itemscreen
import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asFlow
import com.android.netclandemo.ui.composable.MainScreenSearchBar
import com.android.netclandemo.ui.images.MyIconPack
import com.android.netclandemo.ui.images.myiconpack.Filter
import com.android.netclandemo.ui.images.myiconpack.Haha
import com.android.netclandemo.ui.navigation.screens.explorescreen.ExploreViewmodel
import com.android.netclandemo.ui.navigation.screens.explorescreen.fillParentWidth
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun Profile(
    exploreViewmodel: ExploreViewmodel = hiltViewModel(),
    context : Context = LocalContext.current
) {
    val currentstate by exploreViewmodel.serchvalueP.asFlow<String>().collectAsState(initial = "")
    val scope = rememberCoroutineScope()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {

            }) {
                Icon(MyIconPack.Filter, contentDescription ="Filter" )
            }
        },
        topBar = {
            MainScreenSearchBar(onSearchClicked = {
            }, modifier = Modifier.padding(
                start = 20.dp,
                end = 20.dp),
                onsearchQueryListener = {updatedtext->
                    scope.launch {
                        exploreViewmodel.updatesearchP(updatedtext)
                    }
                },
                onClearIconClick = {
                                   scope.launch {
                                       exploreViewmodel.updatesearchP("")
                                   }
                },
                searchQuery = currentstate
            )
        },
        content = { internalPaddingValues->
            ConstraintLayout(modifier = Modifier
                .fillMaxSize()
            ) {
                val (composable) = createRefs()

                Box(
                    modifier = Modifier
                        .constrainAs(composable) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end)
                        }
                ) {
                    Column{
                        Box(
                            modifier = Modifier
                                .align(
                                    Alignment.CenterHorizontally
                                )
                            ,
                        ) {
                            val scale = ContentScale.Fit

                            Image(MyIconPack.Haha,
                                contentDescription =null ,
                                modifier = Modifier
                                    .size(
                                        height = 200.dp,
                                        width = 180.dp
                                    )
                                , contentScale = scale
                            )
                        }
                        val context = LocalContext.current
                        Button(onClick = { Toast.makeText(context , "You Clicked Reload" , Toast.LENGTH_LONG).show()
                        },
                            modifier = Modifier
                                .fillParentWidth()
                                .align(
                                    Alignment.CenterHorizontally
                                )
                        ) {
                            Text(text = "Reload")
                        }
                    }

                }

            }

        }
    )
}

@Preview(name = "Profile")
@Composable
private fun PreviewProfile() {
    Profile()
}