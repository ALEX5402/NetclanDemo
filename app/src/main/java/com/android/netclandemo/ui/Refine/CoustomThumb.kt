package com.android.netclandemo.ui.Refine
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.netclandemo.ui.theme.Purple80

@Composable
fun CustomThumb(sliderPosition: Float) {
    val thumbRadius = 10.dp
    val thumbDiameter = thumbRadius * 2

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier

    ) {
        Box(
            modifier = Modifier
                .offset(y = (-30).dp)  // Adjust this offset to position the magnifier box
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .defaultMinSize(minWidth = 40.dp)
        ) {
            Text(
                text =  "${sliderPosition.toInt()} Km"  ,
                color = Color.Black,
                modifier = Modifier.align(
                    Alignment.Center
                )
            )
        }
        Canvas(modifier = Modifier.size(thumbDiameter)) {
            drawCircle(
                color = Purple80,
                radius = thumbRadius.toPx()
            )
        }
    }
}
@Preview(name = "CoustomThumb")
@Composable
private fun PreviewCoustomThumb() {
    CustomThumb(1f)
}