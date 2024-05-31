package com.android.netclandemo.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview

typealias SearchQueryListener = (searchQuery: String) -> Unit

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Searchbar(
    suggestions: List<String>,
    modifier: Modifier = Modifier,
    value : String,
    searchText : String  = "Search",
    onValueChange: (String) -> Unit,
    onSearchClicked : SearchQueryListener,
    singleLine : Boolean = true,
    enabled : Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    textStyle : TextStyle = TextStyle(color = if (isSystemInDarkTheme()) Color.Black else Color.White),
    content : @Composable () -> Unit
)  {
    var hideKeyboard by remember { mutableStateOf(false) }
    var textFieldValueState by remember { mutableStateOf(TextFieldValue(text = value)) }
    val textFieldValue = textFieldValueState.copy(text = value)
    SideEffect {
        if (textFieldValue.selection != textFieldValueState.selection ||
            textFieldValue.composition != textFieldValueState.composition) {
            textFieldValueState = textFieldValue
        }
    }
    var lastTextValue by remember(value) { mutableStateOf(value) }
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var text by remember { mutableStateOf("") }
    var showSuggestions by remember {
        mutableStateOf(false)
    }
    BasicTextField(
        value = textFieldValue,
            onValueChange = { newTextFieldValueState ->
                textFieldValueState = newTextFieldValueState
                val stringChangedSinceLastInvocation = lastTextValue != newTextFieldValueState.text
                lastTextValue = newTextFieldValueState.text

                if (stringChangedSinceLastInvocation) {
                    onValueChange(newTextFieldValueState.text)
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search ,
                keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(onSearch = {
                focusManager.clearFocus()  // isse wo composable reset ho rha thaa
                focusRequester.restoreFocusedChild()
                showSuggestions = false
                onSearchClicked(textFieldValue.text)
            }),
             modifier = modifier
                 .clickable {
                   //  showSuggestions = false
                     focusRequester.restoreFocusedChild()
                 }
                 .onFocusChanged {
                     isFocused = it.isFocused

                //   focusRequester.requestFocus()
                     showSuggestions = true
                 }
                 .focusRequester(focusRequester),
            singleLine = singleLine,
            enabled = enabled,
            maxLines = maxLines,
            minLines = minLines,
            textStyle = textStyle,
    )
    {
        Row {
            if (text.isEmpty() && !isFocused) {
                Text(
                    text = searchText,
                    style = TextStyle(color = Color.Gray),
                    modifier = Modifier.alpha(0.5f)
                )
            }
            content()
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
    if (hideKeyboard) {
        focusManager.clearFocus()
        focusRequester.restoreFocusedChild()
        hideKeyboard = false
    }
}

// demo
@Composable
fun PreviewDropdown ( ){
    DropdownMenuItem(text = { Text(text = "suggestion example") }, onClick = {})
}
@Preview(name = "Searchbar")
@Composable
private fun PreviewSearchbar() {

    Surface {
        PreviewDropdown()
    }


}