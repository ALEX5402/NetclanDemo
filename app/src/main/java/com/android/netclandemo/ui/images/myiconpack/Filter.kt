package com.android.netclandemo.ui.images.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.android.netclandemo.ui.images.MyIconPack

public val MyIconPack.Filter: ImageVector
    get() {
        if (_filter != null) {
            return _filter!!
        }
        _filter = Builder(name = "Filter", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFFe8eaed)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(440.0f, 800.0f)
                quadToRelative(-17.0f, 0.0f, -28.5f, -11.5f)
                reflectiveQuadTo(400.0f, 760.0f)
                verticalLineToRelative(-240.0f)
                lineTo(168.0f, 224.0f)
                quadToRelative(-15.0f, -20.0f, -4.5f, -42.0f)
                reflectiveQuadToRelative(36.5f, -22.0f)
                horizontalLineToRelative(560.0f)
                quadToRelative(26.0f, 0.0f, 36.5f, 22.0f)
                reflectiveQuadToRelative(-4.5f, 42.0f)
                lineTo(560.0f, 520.0f)
                verticalLineToRelative(240.0f)
                quadToRelative(0.0f, 17.0f, -11.5f, 28.5f)
                reflectiveQuadTo(520.0f, 800.0f)
                horizontalLineToRelative(-80.0f)
                close()
                moveTo(480.0f, 492.0f)
                lineTo(678.0f, 240.0f)
                lineTo(282.0f, 240.0f)
                lineToRelative(198.0f, 252.0f)
                close()
                moveTo(480.0f, 492.0f)
                close()
            }
        }
        .build()
        return _filter!!
    }

private var _filter: ImageVector? = null
