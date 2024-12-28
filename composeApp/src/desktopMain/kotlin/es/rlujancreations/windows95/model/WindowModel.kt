package es.rlujancreations.windows95.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

/**
 * Created by Raúl L.C. on 28/12/24.
 */
data class WindowModel(
    val id: Int,
    val title: String,
    val position: Offset,
    val minimized: Boolean = false,
    val expanded: Boolean = false,
    val selected: Boolean = false,
    val size: DpSize = DpSize(400.dp, 400.dp)
)
