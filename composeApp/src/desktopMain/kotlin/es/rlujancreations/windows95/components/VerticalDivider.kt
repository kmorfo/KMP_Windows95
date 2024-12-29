package es.rlujancreations.windows95.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Created by Raúl L.C. on 27/12/24.
 */
@Composable
fun VerticalDivider() {
    Row(modifier = Modifier.width(8.dp).fillMaxHeight().padding(vertical = 2.dp)) {
        Box(modifier = Modifier.width(2.dp).fillMaxHeight().background(Color.Black.copy(0.5f)))
        Box(modifier = Modifier.width(2.dp).fillMaxHeight().background(Color.White))
    }
}