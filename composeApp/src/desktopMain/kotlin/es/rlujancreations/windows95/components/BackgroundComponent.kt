package es.rlujancreations.windows95.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import es.rlujancreations.windows95.ui.backgroundComponent

/**
 * Created by Raúl L.C. on 27/12/24.
 */
@Composable
fun BackgroundComponent(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    strokeWidth: Float = 1.5f,
    spacing: Float = 1.5f,
    content: @Composable () -> Unit
) {
    Box(modifier.background(backgroundComponent).drawBehind {
        val width = size.width
        val height = size.height

        //Left back 1st line
        drawLine(
            color = if (selected) Color.Black else Color.White,
            start = Offset(0f, 0f),
            end = Offset(0f, height),
            strokeWidth = strokeWidth
        )

        //Left back 2nd line
        drawLine(
            color = if (selected) Color.DarkGray else Color.White,
            start = Offset(spacing, 0f),
            end = Offset(spacing, height),
            strokeWidth = strokeWidth
        )

        //Top 1st line
        drawLine(
            color = if (selected) Color.Black else Color.White,
            start = Offset(0f, 0f),
            end = Offset(width, 0f),
            strokeWidth = strokeWidth
        )

        //Top 2nd line
        drawLine(
            color = if (selected) Color.DarkGray else Color.White,
            start = Offset(0f, spacing),
            end = Offset(width, spacing),
            strokeWidth = strokeWidth
        )


        //Right back 1st line
        drawLine(
            color = if (selected) Color.White else Color.Black,
            start = Offset(width, 0f),
            end = Offset(width, height),
            strokeWidth = strokeWidth
        )

        //Right back 2nd line
        drawLine(
            color = if (selected) Color.White else Color.DarkGray,
            start = Offset(width - spacing, 0f),
            end = Offset(width - spacing, height),
            strokeWidth = strokeWidth
        )


        //Bottom 1st line
        drawLine(
            color = if (selected) Color.White else Color.Black,
            start = Offset(0f, height),
            end = Offset(width, height),
            strokeWidth = strokeWidth
        )

        //Bottom 2nd line
        drawLine(
            color = if (selected) Color.White else Color.DarkGray,
            start = Offset(0f, height - spacing),
            end = Offset(width, height - spacing),
            strokeWidth = strokeWidth
        )
    }) {
        content()
    }
}