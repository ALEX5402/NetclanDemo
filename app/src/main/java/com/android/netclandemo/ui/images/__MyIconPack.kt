package com.android.netclandemo.ui.images

import androidx.compose.ui.graphics.vector.ImageVector
import com.android.netclandemo.ui.images.myiconpack.Filter
import kotlin.collections.List as ____KtList

public object MyIconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val MyIconPack.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(Filter)
    return __AllIcons!!
  }
