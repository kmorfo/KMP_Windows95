package es.rlujancreations.windows95.components.window

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import es.rlujancreations.windows95.ui.backgroundComponent

/**
 * Created by Raúl L.C. on 30/12/24.
 */
@Composable
fun WindowStatusBar(modifier: Modifier = Modifier, items: Int = 0) {
    Box(modifier.background(backgroundComponent).drawBehind {
        val width = size.width
        val height = size.height

        //Left back 1st line
        drawLine(
            color = Color.DarkGray,
            start = Offset(0f, 0f),
            end = Offset(0f, height),
            strokeWidth = 2.5f,
        )

        //Left back 2nd line
        drawLine(
            color = Color.LightGray,
            start = Offset(1f, 0f),
            end = Offset(1f, height),
            strokeWidth = 3.5f,
        )

        //Top 1st line
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, 0f),
            end = Offset(width, 0f),
            strokeWidth = 5f
        )

        //Top 2nd line
        drawLine(
            color = Color.DarkGray,
            start = Offset(0f, 1f),
            end = Offset(width, 1f),
            strokeWidth = 1f
        )


        //Right back 1st line
        drawLine(
            color = Color.White,
            start = Offset(width, 0f),
            end = Offset(width, height),
            strokeWidth = 1f
        )

        //Right back 2nd line
        drawLine(
            color = Color.White,
            start = Offset(width - 1f, 0f),
            end = Offset(width - 1f, height),
            strokeWidth = 1f
        )


        //Bottom 1st line
        drawLine(
            color = Color.White,
            start = Offset(0f, height),
            end = Offset(width, height),
            strokeWidth = 1f
        )

        //Bottom 2nd line
        drawLine(
            color = Color.White,
            start = Offset(0f, height - 1f),
            end = Offset(width, height - 1f),
            strokeWidth = 1f
        )

        //Separator back 1st line
        drawLine(
            color = Color.White,
            start = Offset((width / 1.7).toFloat(), 0f),
            end = Offset((width / 1.7).toFloat(), height),
            strokeWidth = 1f
        )

        //Separator back 2nd line
        drawLine(
            color = Color.LightGray,
            start = Offset((width / 1.7).toFloat() - 1f, 0f),
            end = Offset((width / 1.7).toFloat() - 1f, height),
            strokeWidth = 2.2f
        )

        //Separator back 3nd line
        drawLine(
            color = Color.DarkGray.copy(alpha = 0.6f),
            start = Offset((width / 1.7).toFloat() + 3.2f, 0f),
            end = Offset((width / 1.7).toFloat() + 3.2f, height),
            strokeWidth = 2.2f
        )

        //Expand indicator lines
        drawLine(
            color = Color.LightGray,
            start = Offset(width, 10f),
            end = Offset(width-18f, height),
            strokeWidth = 1f
        )
        drawLine(
            color = Color.DarkGray.copy(alpha = 0.8f),
            start = Offset(width, 12f),
            end = Offset(width-16f, height),
            strokeWidth = 1f
        )
        drawLine(
            color = Color.LightGray,
            start = Offset(width, 15f),
            end = Offset(width-13f, height),
            strokeWidth = 1f
        )
        drawLine(
            color = Color.DarkGray.copy(alpha = 0.8f),
            start = Offset(width, 17f),
            end = Offset(width-11f, height),
            strokeWidth = 1f
        )
        drawLine(
            color = Color.LightGray,
            start = Offset(width, 20f),
            end = Offset(width-8f, height),
            strokeWidth = 1f
        )
        drawLine(
            color = Color.DarkGray.copy(alpha = 0.8f),
            start = Offset(width, 22f),
            end = Offset(width-6f, height),
            strokeWidth = 1f
        )

    }) {
        Row(
            modifier = modifier.fillMaxWidth().height(28.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("$items objects(s)", modifier = Modifier.padding(start = 4.dp))
        }
    }

}