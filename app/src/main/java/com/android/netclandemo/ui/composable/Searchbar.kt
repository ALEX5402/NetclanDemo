@file:OptIn(ExperimentalMaterial3Api::class)

package com.android.netclandemo.ui.composable


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp

typealias SearchQueryListener = (searchQuery: String) -> Unit


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreenSearchBar(
    onSearchClicked : SearchQueryListener,
    modifier: Modifier = Modifier,
    searchQuery: String,
    onClearIconClick : () -> Unit,
    onsearchQueryListener: (String) -> Unit,
)  {

    var clearIconState by remember {
        mutableStateOf(false)
    }
    val keyboardState by rememberKeyboardVisibilityState()

    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    var isActive by remember {
        mutableStateOf(false)
    }
        SearchBar(query = searchQuery ,
            onQueryChange = { updatedText->
                onsearchQueryListener(updatedText)
            } ,
            onSearch = { searchQuery-> onSearchClicked(searchQuery)
                focusManager.clearFocus()
            } ,
            active = isActive ,
            onActiveChange = { onchangeBoolean->

            },
            trailingIcon = {
               AnimatedVisibility(visible = clearIconState) {
                   Icon(Icons.Outlined.Clear,
                       contentDescription = "Clear",
                       modifier = Modifier.clickable {
                           onClearIconClick()
                       }

                   )
               }
            },

            leadingIcon = {
                Icon(Icons.Default.Search,
                        contentDescription = "Search")
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

    LaunchedEffect(key1 = searchQuery) {
        if (searchQuery.isNotEmpty()){
            clearIconState = true
        }else{
            clearIconState = false
        }
    }


    LaunchedEffect( key1 = isFocused) {
        if (!isFocused){
            focusManager.clearFocus()
            focusRequester.restoreFocusedChild()
        }
    }

}