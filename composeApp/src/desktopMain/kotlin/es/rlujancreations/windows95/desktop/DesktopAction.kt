package es.rlujancreations.windows95.desktop

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntOffset
import es.rlujancreations.windows95.domain.model.FileSortType
import es.rlujancreations.windows95.domain.model.FolderModel

/**
 * Created by Raúl L.C. on 30/12/24.
 */
sealed interface DesktopAction {
    data object OnUnselectWindows : DesktopAction
    data object OnClearFiles : DesktopAction
    data object OnClearWindows : DesktopAction

    data class OnCreateFolder(val position: Offset) : DesktopAction
    data class OnClickRightClick(val position: IntOffset) : DesktopAction
    data class OnFileMove(val fileId: Int, val offset: Offset) : DesktopAction
    data class OnRenameFile(val fileId: Int, val newName: String) : DesktopAction
    data class OnTabFile(val fileId: Int) : DesktopAction
    data class OnDoubleTabFile(val file: FolderModel) : DesktopAction

    data class OnSortFiles(val sortType: FileSortType) : DesktopAction

    data object OnDismissRightClickMenu : DesktopAction

    data object OnToggleWindowsMenu : DesktopAction
    data class OnClickMinimizedWindow(val windowId: Int) : DesktopAction

    data class OnWindowMove(val windowId: Int, val offset: Offset) : DesktopAction
    data class OnWindowClose(val windowId: Int) : DesktopAction
    data class OnWindowMinimize(val windowId: Int) : DesktopAction
    data class OnWindowExpand(val windowId: Int) : DesktopAction
    data class OnWindowClick(val windowId: Int) : DesktopAction
}