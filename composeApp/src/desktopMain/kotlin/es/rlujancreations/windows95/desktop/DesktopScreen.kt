package es.rlujancreations.windows95.desktop

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.runtime.key
import es.rlujancreations.windows95.components.DraggableFile
import es.rlujancreations.windows95.components.RightClickMenu
import es.rlujancreations.windows95.components.window.DraggableWindow
import es.rlujancreations.windows95.components.windowsbarmenu.WindowsBar
import es.rlujancreations.windows95.components.windowsbarmenu.WindowsBarMenuScreen
import es.rlujancreations.windows95.extensions.clickableWithoutRipple
import es.rlujancreations.windows95.extensions.onRightClick
import org.koin.compose.viewmodel.koinViewModel

/**
 * Created by Raúl L.C. on 26/12/24.
 */
@Composable
fun Desktop(
    desktopViewModel: DesktopViewModel = koinViewModel()
) {
    val state by desktopViewModel.state.collectAsState()

    Column {
        Box(
            modifier = Modifier.fillMaxWidth().weight(1f)
                .clickableWithoutRipple {
                    desktopViewModel.onAction(DesktopAction.OnClearWindows)
                }.onRightClick {
                    desktopViewModel.onAction(DesktopAction.OnClickRightClick(it))
                }) {
            state.folders.forEach { folder ->
                DraggableFile(
                    folder,
                    onMove = { newPosition ->
                        desktopViewModel.onAction(DesktopAction.OnFileMove(folder.id, newPosition))
                    },
                    onTapFolder = { id ->
                        desktopViewModel.onAction(DesktopAction.OnTabFile(fileId = id))
                    },
                    onRename = { newName ->
                        desktopViewModel.onAction(DesktopAction.OnRenameFile(folder.id, newName))
                    },
                    onDoubleTapFolder = { selectedFolder ->
                        desktopViewModel.onAction(DesktopAction.OnDoubleTabFile(selectedFolder))
                    }
                )
            }
            WindowsBarMenuScreen(showWindowsMenu = state.showWindowsMenu)
            state.windows.filter { !it.minimized }.forEach { window ->
                key(window.id) {
                    DraggableWindow(
                        windowModel = window,
                        onMove = { offset ->
                            desktopViewModel.onAction(DesktopAction.OnWindowMove(window.id, offset))
                        },
                        onClose = {
                            desktopViewModel.onAction(DesktopAction.OnWindowClose(window.id))
                        },
                        onMinimize = {
                            desktopViewModel.onAction(DesktopAction.OnWindowMinimize(window.id))
                        },
                        onExpand = {
                            desktopViewModel.onAction(DesktopAction.OnWindowExpand(window.id))
                        },
                        onClick = {
                            desktopViewModel.onAction(DesktopAction.OnWindowClick(window.id))
                        }
                    )
                }
            }
            RightClickMenu(
                showMenu = state.showRightClickMenu,
                position = state.rightClickPosition,
                onDismissRequest = { DesktopAction.OnDismissRightClickMenu },
                createNewFolder = {
                    desktopViewModel.onAction(
                        DesktopAction.OnCreateFolder(Offset(it.x.toFloat(), it.y.toFloat()))
                    )
                },
                sortFolders = { sortType ->
                    desktopViewModel.onAction(DesktopAction.OnSortFiles(sortType))
                }
            )
        }
        WindowsBar(
            windows = state.windows,
            onClickMinimizedWindow = { window ->
                desktopViewModel.onAction(DesktopAction.OnClickMinimizedWindow(window.id))
            }) {
            desktopViewModel.onAction(DesktopAction.OnToggleWindowsMenu)
        }
    }
}

