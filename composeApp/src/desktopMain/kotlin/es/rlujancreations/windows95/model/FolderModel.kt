package es.rlujancreations.windows95.model

import androidx.compose.ui.geometry.Offset

/**
 * Created by Raúl L.C. on 27/12/24.
 */
data class FolderModel(
    val id: Int,
    val name: String = "New folder",
    val position: Offset,
    val selected: Boolean = false
)