package com.android.netclandemo.ui.composable

import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun rememberKeyboardVisibilityState(): State<Boolean> {
    val keyboardController = LocalSoftwareKeyboardController.current
    val view = LocalView.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val isKeyboardOpen = remember { mutableStateOf(false) }

    DisposableEffect(view, lifecycleOwner) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view.height
            val keypadHeight = screenHeight - rect.bottom

            // Check if the keyboard is open
            isKeyboardOpen.value = keypadHeight > screenHeight * 0.15
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(listener)

        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }

    return isKeyboardOpen
}