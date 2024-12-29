package es.rlujancreations.windows95.model

import androidx.compose.ui.geometry.Offset
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.jetbrains.compose.resources.DrawableResource
import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.ic_folder

/**
 * Created by Raúl L.C. on 27/12/24.
 */
data class FolderModel(
    val id: Int,
    val name: String = "New folder",
    val position: Offset,
    val createdDate: Instant = Clock.System.now(),
    val selected: Boolean = false,
    val icon: DrawableResource = Res.drawable.ic_folder
)