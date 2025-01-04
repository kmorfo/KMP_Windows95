package es.rlujancreations.windows95.desktop

import androidx.compose.ui.unit.IntOffset
import es.rlujancreations.windows95.domain.model.FileModel
import es.rlujancreations.windows95.model.WindowModel

/**
 * Created by Raúl L.C. on 30/12/24.
 */
data class DesktopState(
    val showWindowsMenu: Boolean = false,
    val files: List<FileModel> = emptyList(),
    val windows: List<WindowModel> = emptyList(),
    val showRightClickMenu: Boolean = false,
    val rightClickPosition: IntOffset = IntOffset.Zero,
    val rightClickFile: FileModel? = null,
    val activeWindow: Int? = null
)