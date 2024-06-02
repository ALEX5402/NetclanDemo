@file:OptIn(ExperimentalMaterial3Api::class)

package com.android.netclandemo.ui.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.sharp.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asFlow
import com.android.netclandemo.ui.navigation.screens.explorescreen.ExploreViewmodel
import kotlinx.coroutines.launch

typealias SearchQueryListener = (searchQuery: String) -> Unit


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreenSearchBar(
    suggestions: List<String>,
    onSearchClicked : SearchQueryListener,
    modifier: Modifier,
    viewmodel: ExploreViewmodel = hiltViewModel(),
)  {
    var hideKeyboard by remember { mutableStateOf(false) }
    val keyboardState by rememberKeyboardVisibilityState()

    val scope = rememberCoroutineScope()
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var showSuggestions by remember {
        mutableStateOf(false)
    }
    val currentValue by viewmodel.serchvalue.asFlow<String>().collectAsState(initial = "")
    var isActive by remember {
        mutableStateOf(false)
    }
            SearchBar(query = currentValue ,
                onQueryChange = { updatedText->
                    scope.launch {
                        viewmodel.updatesearch(updatedText)
                    }
                } ,
                onSearch = { searchQuery-> onSearchClicked(searchQuery)
                    focusManager.clearFocus()
                } ,
                active = isActive ,
                onActiveChange = { onchangeBoolean->
                   showSuggestions = onchangeBoolean
                },

                leadingIcon = {
                    Icon(Icons.Default.Search,
                        contentDescription = null)
                },
                shape = RoundedCornerShape(40.dp),
                placeholder = {
                    Text(text = "Search")
                } ,
                shadowElevation = SearchBarDefaults.InputFieldHeight ,

                modifier = modifier
                    .focusRequester(focusRequester)
                    .onFocusEvent { focusEvent ->
                        isFocused = focusEvent.isFocused

                    }

            ){}

        val suggetion = suggestions.filter {
            it.contains(currentValue, ignoreCase = true)
        }
            if (isFocused && showSuggestions){
                DropdownMenu(
                    expanded = true,
                    onDismissRequest = {
                        showSuggestions = false
                    },
                    Modifier.fillMaxWidth()
                ) {
                  suggetion.take(4).forEachIndexed { index, items ->
                          DropdownMenuItem(text = {
                              Text(text = items)
                          }, onClick = {
                              scope.launch {
                                  viewmodel.updatesearch(items)
                              }
                              showSuggestions = false
                            //  focusManager.clearFocus()
                          }, trailingIcon = {
                              Icon(Icons.Sharp.KeyboardArrowUp,
                                  contentDescription = null )
                          })
                  }

                }
            }

    LaunchedEffect(key1 = currentValue ) {
        showSuggestions = true

    }

    LaunchedEffect( key1 = isFocused) {
        if (!isFocused){
            showSuggestions = false
            focusManager.clearFocus()
            focusRequester.restoreFocusedChild()
        }
    }


    /*if (isFocused && showSuggestions) {
        DropdownMenu(
            expanded = true,
            onDismissRequest = { showSuggestions = false }
        ) {
            val filteredSuggestions = suggestions.filter { it.contains(text, ignoreCase = true) }
            filteredSuggestions.forEach { suggestion ->
                DropdownMenuItem(text = {
                    Text(text = suggestion)
                }, onClick = {
                    text = suggestion
                    showSuggestions = false
                    focusManager.clearFocus()
                })
            }
        }
    }*/

}

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






// demo

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
private fun Preview122() {
    var value by mutableStateOf("")

    MainScreenSearchBar(suggestions = names, onSearchClicked = {

    } , modifier = Modifier)

}