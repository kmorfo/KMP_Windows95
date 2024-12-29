package es.rlujancreations.windows95.desktop

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.runtime.key
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntOffset
import es.rlujancreations.windows95.components.DraggableFolder
import es.rlujancreations.windows95.components.RightClickMenu
import es.rlujancreations.windows95.components.window.DraggableWindow
import es.rlujancreations.windows95.components.windowsbarmenu.WindowsBar
import es.rlujancreations.windows95.components.windowsbarmenu.WindowsBarMenuScreen
import es.rlujancreations.windows95.extensions.clickableWithoutRipple
import es.rlujancreations.windows95.extensions.onRightClick
import es.rlujancreations.windows95.model.FolderModel
import es.rlujancreations.windows95.model.WindowModel

/**
 * Created by Raúl L.C. on 26/12/24.
 */
@Composable
fun Desktop() {
    var showWindowsMenu by remember { mutableStateOf(false) }
    val fakeFolder = FolderModel(0, "RLujanCreations", position = Offset(0f, 0f))
    val fakeFolder2 = FolderModel(1, "Aris", position = Offset(0f, 80f))
    var folders by remember { mutableStateOf(listOf<FolderModel>(fakeFolder, fakeFolder2)) }
    var windows by remember { mutableStateOf(listOf<WindowModel>()) }

    var showRightClickMenu by remember { mutableStateOf(false) }
    var rightClickPosition by remember { mutableStateOf(IntOffset.Zero) }

    Column {
        Box(
            modifier = Modifier.fillMaxWidth().weight(1f)
                .clickableWithoutRipple {
                    showWindowsMenu = false
                    showRightClickMenu = false
                    folders = clearFolders(folders)
                    windows = unselectWindows(windows)
                }.onRightClick {
                    rightClickPosition = it
                    showRightClickMenu = true
                }) {
            folders.forEach { folder ->
                DraggableFolder(
                    folder,
                    onMove = { newPosition ->
                        folders = folders.map {
                            if (it.id == folder.id) it.copy(position = newPosition) else it
                        }
                    },
                    onTapFolder = { id ->
                        folders = folders.map { it.copy(selected = id == it.id) }
                    },
                    onRename = { newName ->
                        folders = folders.map {
                            if (it.id == folder.id) it.copy(name = newName) else it
                        }
                    },
                    onDoubleTapFolder = { selectedFolder ->
                        if (windows.any { it.id == selectedFolder.id }) {
                            windows =
                                windows.map {
                                    if (it.id == selectedFolder.id) it.copy(
                                        selected = true,
                                        minimized = false,
                                    ) else it.copy(selected = false)
                                }
                        } else {
                            val extraPosition = windows.size * 10
                            windows = unselectWindows(windows)
                            val newWindow = WindowModel(
                                id = selectedFolder.id,
                                title = selectedFolder.name,
                                selected = true,
                                position = Offset(100f + extraPosition, 100f + extraPosition)
                            )
                            windows = windows + newWindow
                        }
                    }
                )
            }
            WindowsBarMenuScreen(showWindowsMenu = showWindowsMenu)
            windows.filter { !it.minimized }.forEach { window ->
                key(window.id) {
                    DraggableWindow(
                        windowModel = window,
                        onMove = { offset ->
                            windows = windows.map {
                                if (it.id == window.id) it.copy(position = offset) else it
                            }
                        },
                        onClose = { windows = windows.filter { it.id != window.id } },
                        onMinimize = {
                            windows =
                                windows.map {
                                    if (it.id == window.id) it.copy(
                                        minimized = true,
                                        selected = false
                                    ) else it
                                }
                        },
                        onExpand = {
                            windows =
                                windows.map {
                                    if (it.id == window.id) it.copy(
                                        expanded = !it.expanded,
                                        selected = true
                                    ) else it
                                }
                        },
                        onClick = {
                            windows =
                                windows.map {
                                    if (it.id == window.id) it.copy(selected = true) else it.copy(
                                        selected = false
                                    )
                                }
                        }
                    )
                }
            }
            RightClickMenu(
                showMenu = showRightClickMenu,
                position = rightClickPosition,
                onDismissRequest = { showRightClickMenu = false },
                createNewFolder = {
                    val newFolder = FolderModel(
                        id = folders.size + 1, position = Offset(it.x.toFloat(), it.y.toFloat())
                    )
                    folders = folders + newFolder
                    showRightClickMenu = false
                },
            )
        }
        WindowsBar(
            windows = windows, onClickMinimizedWindow = { window ->
                windows = windows.map {
                    if (window.id == it.id) it.copy(minimized = !it.minimized) else it
                }
            }) { showWindowsMenu = !showWindowsMenu }
    }
}

fun unselectWindows(windows: List<WindowModel>): List<WindowModel> {
    return windows.map { it.copy(selected = false) }
}

fun clearFolders(folders: List<FolderModel>): List<FolderModel> {
    return folders.map { it.copy(selected = false) }
}
