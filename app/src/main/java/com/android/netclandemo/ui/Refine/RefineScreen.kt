package com.android.netclandemo.ui.Refine

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asFlow
import com.android.netclandemo.ui.navigation.screens.explorescreen.fillParentWidth
import com.android.netclandemo.ui.theme.lightblue1
import kotlinx.coroutines.launch


typealias Backclick = () -> Unit
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun RefineScreen(
    onClickBack : Backclick,
    viewmodel: RefineViewmodel = hiltViewModel()
) {
    Scaffold(modifier = Modifier
        .scrollable(
                state = rememberScrollState(),
        enabled = true,
        orientation = Orientation.Vertical

    ),
    topBar = {
        TopAppBar(title = {
            Text(text = "Refine",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(
                    start = 5.dp
                )
            )
        },
           navigationIcon = {
               Icon(Icons.Default.KeyboardArrowDown,
                   contentDescription = null,
                   modifier = Modifier
                       .rotate(
                           90f
                       )
                       .size(30.dp)
                       .clickable {
                           onClickBack()
                       }
               )
           }
        )
    }
    ) { internalPadding->


        val textState by viewmodel.statusText.asFlow<String>().collectAsState(initial = "")
        val scope = rememberCoroutineScope()



        val createref = ConstraintSet {
            val  container1 = createRefFor("container1")
            val  container2 = createRefFor("container2")
            val  container3 = createRefFor("container3")
            val  container4 = createRefFor("container4")

            constrain(container1){
                top.linkTo(parent.top)
                bottom.linkTo(container2.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            constrain(container2){
                top.linkTo(container1.bottom)
                bottom.linkTo(container3.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            constrain(container3){
                top.linkTo(container2.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

            }
            constrain(container4){
                top.linkTo(container1.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

            }


        }

        var spinnerText by remember {
            mutableStateOf("Default Text")
        }
        var rotated by remember { mutableStateOf(false) }
        val rotationAngle by animateFloatAsState(if (rotated) 180f else 0f)

        val itemsss = listOf(
            "Purpose1",
            "Purpose2",
            "Purpose3",
            "Purpose4",
            "extra",
        )

        val itemsss2 = listOf(
            "My Current Option 1",
            "My Current Option 2",
            "My Current Option 3",
            "My Current Option 4",
            "My Current Option 5",
        )


        var showSpinner by remember {
            mutableStateOf(false  )
        }

        ConstraintLayout (
            modifier = Modifier
                .fillMaxSize()
                .padding(internalPadding.apply {
                    calculateTopPadding().plus(20.dp)
                })
            ,
            constraintSet = createref
        ){

           Column (modifier = Modifier
               .layoutId("container1")

           ){
               Text(text = "Select Your Availability",
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(
                           start = 20.dp,
                           bottom = 10.dp,
                           top = 30.dp

                       ))
                   Row(
                       modifier = Modifier
                           .padding(
                               start = 20.dp,
                               end = 20.dp
                           )
                           .align(
                               Alignment.CenterHorizontally
                           )
                           .border(1.dp, lightblue1, shape = RoundedCornerShape(10.dp))
                           .clickable {
                               rotated = !rotated
                               showSpinner = !showSpinner
                           }
                   ) {
                           Text(text = spinnerText,
                               textAlign = TextAlign.Start,
                               modifier = Modifier
                                   .align(
                                       Alignment.CenterVertically
                                   )
                                   .padding(
                                       start = 10.dp,
                                       top = 10.dp,
                                       bottom = 10.dp
                                   )
                                   .weight(1f)

                           )


                           Image(imageVector = Icons.Default.KeyboardArrowDown ,
                               contentDescription = "DropDown menu",
                               alignment = Alignment.Center,
                               modifier = Modifier
                                   .padding(10.dp)
                                   .graphicsLayer(rotationZ = rotationAngle)
                           )
                   }
           }
            Box(
                modifier = Modifier
                    .layoutId("container4")
                    .padding(
                        start = 20.dp,
                        end = 20.dp
                    )
                    .fillMaxWidth()
            ) {
                AnimatedVisibility(visible = showSpinner ) {
                        DropdownMenu(expanded = true,
                            onDismissRequest = {
                                showSpinner = false
                                rotated = false
                            }, modifier = Modifier
                                .height(
                                    250.dp
                                )
                        ) {
                            itemsss2.forEachIndexed { index, s ->
                                DropdownMenuItem(text = {
                                    Text(text = s ,
                                        modifier = Modifier.weight(1f)
                                        ,
                                        textAlign = TextAlign.Start
                                    )
                                    Spacer(modifier = Modifier.fillParentWidth())
                                }, onClick = {
                                    spinnerText = s
                                    showSpinner = false
                                    rotated = false
                                },
                                    modifier = Modifier

                                )

                            }
                        }
                }
            }

            var textsize by remember {
                mutableIntStateOf(0)
            }
            var iserror by remember {
                mutableStateOf(false)
            }

            Column (modifier = Modifier
               .layoutId("container2")

           ){
                Text(text = "Add Your Stars",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 20.dp,
                            top = 30.dp

                        ))
                Row(
                    modifier = Modifier
                        .padding(
                            start = 20.dp,
                            end = 20.dp
                        )
                        .align(
                            Alignment.CenterHorizontally
                        )) {

                    OutlinedTextField(value = textState,
                        onValueChange = {updatedText->
                        scope.launch {
                            textsize = updatedText.length
                            viewmodel.updateStatustext(updatedText)
                        }
                    },
                        isError = iserror,
                        label = {
                        Text(text = """Hi community!I am open for new connections "😊" """,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .align(
                                    Alignment.CenterVertically
                                )
                                .basicMarquee()
                        )
                    },
                   trailingIcon = {
                       if (textsize > 250 ){
                           Icon(Icons.Default.Warning ,
                               contentDescription =null)
                           iserror = true
                       }else{
                           iserror = false
                       }
                   },
                   modifier = Modifier.fillMaxWidth()


                        ,
                        shape = RoundedCornerShape(10.dp)
                    )

                }
                Text(text = "$textsize/250",
                    textAlign =  TextAlign.Right,
                    modifier = Modifier
                        .padding(
                            end = 20.dp,
                            top = 10.dp

                        )
                        .fillMaxWidth()

                )
            }
            Column (modifier = Modifier
               .layoutId("container3")

           ){
                var sliderPosition = viewmodel.sliderState

                Text(text = "Select Hyper Local Distance",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 20.dp,
                            end = 20.dp
                        ))
                Slider(
                    value = sliderPosition,
                    onValueChange = { scope.launch {
                        viewmodel.updateSlider(it)
                    } },
                    valueRange = 1f..100f,

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 20.dp,
                            start = 20.dp,
                            end = 20.dp
                        ),
                    thumb = {
                        CustomThumb(sliderPosition)
                    }
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "${sliderPosition.toInt()} Km",
                        modifier = Modifier.padding(
                            start = 20.dp
                        )

                        )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "100 Km",
                        modifier = Modifier.padding(
                            end = 20.dp
                        )
                        )

                }
                Text(text = "Select Purpose",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 30.dp,
                            start = 20.dp,
                            end = 20.dp
                        ))
                val selectedchips = remember {
                    mutableListOf(false)
                }

                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 120.dp),
                    modifier = Modifier,
                    contentPadding = PaddingValues(
                        end = 20.dp,
                        top = 10.dp
                    )
                ) {
                    val isselected = listOf(
                        mutableStateOf(false),
                        mutableStateOf(false),
                        mutableStateOf(false),
                        mutableStateOf(false),
                        mutableStateOf(false),
                        mutableStateOf(false),
                    )

                    items(itemsss.size) { index->
                        val selecteditem = isselected[index]
                        ChipButton(isSelected = selecteditem.value ,
                            onclick = {
                                selecteditem.value = !selecteditem.value

                        }, label = itemsss[index],
                            modifier = Modifier.padding(
                                start = 20.dp
                            )

                        )
                    }
                }
                val context = LocalContext.current
                Box(modifier = Modifier.fillMaxWidth()) {

                    Button(onClick = {

                    },
                        modifier = Modifier
                            .fillParentWidth()
                            .align(
                                Alignment.Center
                            )
                    ) {
                        Text(text = "Save & Explore")
                    }
                }
            }
        }
    }
}




@Preview(name = "RefineScreen",)
@Composable
private fun PreviewRefineScreen() {
    RefineScreen({})
}