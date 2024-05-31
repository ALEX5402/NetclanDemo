@file:OptIn(ExperimentalMaterial3Api::class)

package com.android.netclandemo.ui.navigation.screens.explorescreen

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asFlow
import com.android.netclandemo.R
import com.android.netclandemo.ui.composable.Searchbar


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable

fun ExplorepageByIndex(
    currentpage : Int = 0 ,
    tabitems : String,
    exploreViewmodel: ExploreViewmodel = hiltViewModel(),
    context : Context = LocalContext.current
) {
    Scaffold(
        bottomBar = {

        },
        topBar = {
            val textFieldColors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0XFF101921),
                focusedTextColor = Color(0xFF2F6CA2)
            )
            val srarchstate by exploreViewmodel.serchvalue.asFlow<String>().collectAsState(initial = "")
            val scope = rememberCoroutineScope()







        },
        content = {
            val names = listOf(
            "Alice",
            "Bob",
            "Charlie",
            "Diana",
            "Edward",
            "Fiona",
            "George",
            "Hannah",
            "Isaac",
            "Jasmine",
            "Kevin",
            "Lily",
            "Michael",
            "Nina",
            "Oliver",
            "Paula",
            "Quincy",
            "Rachel",
            "Samuel",
            "Tina"
        )
            ConstraintLayout(modifier = Modifier
                .fillMaxSize()
            ) {
                val srarchstate by exploreViewmodel.serchvalue.asFlow<String>().collectAsState(initial = "")

                Searchbar(suggestions = names,
                    value = srarchstate ,
                    onValueChange = { updatedvale->
                        exploreViewmodel.updateindex(updatedvale)
                    },
                    onSearchClicked ={

                    },
                    modifier = Modifier
                        .padding(
                            start = 10.dp,
                            end = 10.dp,
                            top = 53.dp
                        )
                        .background(Color.Red, shape = RoundedCornerShape(30.dp) )
                        .fillMaxWidth()

                ) {

                }
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
                            Image(painter = painterResource(id = R.drawable.ic_launcher_foreground),
                                contentDescription =null ,
                                modifier = Modifier
                                    .size(50.dp)
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
                            Text(text = "Reload $tabitems")
                        }
                    }

                }

            }

        }
    )
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
        ""
    )
}