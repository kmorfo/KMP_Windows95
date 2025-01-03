package es.rlujancreations.windows95.domain.model

import androidx.compose.ui.geometry.Offset
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

/**
 * Created by Raúl L.C. on 27/12/24.
 */
data class FileModel(
    val id: Int,
    val path: String = "Desktop",
    val type: FileType = FileType.Folder,
    val name: String = "New folder",
    val position: Offset,
    val createdDate: Instant = Clock.System.now(),
    val selected: Boolean = false,
    val icon: String = "ic_folder"
)