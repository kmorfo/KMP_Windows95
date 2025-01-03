package es.rlujancreations.windows95.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import es.rlujancreations.windows95.components.rightmenu.MenuDivider
import es.rlujancreations.windows95.components.rightmenu.MenuItem
import es.rlujancreations.windows95.components.rightmenu.SubMenu
import es.rlujancreations.windows95.domain.model.FileModel
import es.rlujancreations.windows95.domain.model.FileSortType
import es.rlujancreations.windows95.domain.model.FileSortType.ByDate
import es.rlujancreations.windows95.domain.model.FileSortType.ByName
import es.rlujancreations.windows95.domain.model.FileSortType.ByType
import es.rlujancreations.windows95.domain.model.SubMenuItem

/**
 * Created by Raúl L.C. on 28/12/24.
 */
@Composable
fun RightClickMenu(
    showMenu: Boolean,
    position: IntOffset,
    onDismissRequest: () -> Unit,
    createNewFolder: (IntOffset) -> Unit,
    sortFolders: (FileSortType) -> Unit,
    rightClickFile: FileModel?,
    openFile: () -> Unit,
    removeFile: () -> Unit,
    renameFile: () -> Unit
) {
    var subMenuPosition: IntOffset? by remember { mutableStateOf(null) }
    var subMenuItems: List<SubMenuItem> by remember { mutableStateOf(emptyList()) }

    LaunchedEffect(position) {
        subMenuPosition = null
    }

    AnimatedVisibility(showMenu, exit = ExitTransition.None, enter = fadeIn()) {
        Popup(
            offset = position,
            onDismissRequest = { onDismissRequest() },
            alignment = Alignment.TopStart
        ) {
            BackgroundComponent(Modifier.width(170.dp)) {
                if (rightClickFile != null) {
                    Column(Modifier.padding(3.dp)) {
                        MenuItem(
                            text = "Open",
                            isTextBold = true,
                            onClick = { openFile() },
                            hovered = { subMenuPosition = null })
                        MenuDivider()
                        MenuItem(text = "Send To",
                            showSubMenu = true,
                            hovered = {
                                subMenuItems = listOf(
                                    SubMenuItem(
                                        title = "3½ Floppy (A)",
                                        onClick = { sortFolders(ByName) }),
                                    SubMenuItem(
                                        title = "5 1⁄4 Floppy (B)",
                                        onClick = { sortFolders(ByName) }),
                                )
                                val extraY: Int = it?.y?.toInt() ?: 0
                                subMenuPosition = IntOffset(position.x + 163, position.y + extraY)
                            })
                        MenuDivider()
                        MenuItem(
                            text = "Cut",
                            onClick = { },
                            hovered = { subMenuPosition = null })
                        MenuItem(
                            text = "Copy",
                            onClick = { },
                            hovered = { subMenuPosition = null })
                        MenuDivider()
                        MenuItem(
                            text = "Create Shortcut",
                            onClick = { },
                            hovered = { subMenuPosition = null })
                        MenuItem(
                            text = "Delete",
                            onClick = { removeFile() },
                            hovered = { subMenuPosition = null })
                        MenuItem(
                            text = "Rename",
                            onClick = { renameFile() },
                            hovered = { subMenuPosition = null })
                        MenuDivider()
                        MenuItem(
                            text = "Properties",
                            onClick = { },
                            hovered = { subMenuPosition = null })
                    }
                } else {
                    Column(Modifier.padding(3.dp)) {
                        MenuItem(text = "Arrange Icons",
                            showSubMenu = true,
                            hovered = {
                                subMenuItems = listOf(
                                    SubMenuItem("By name", onClick = { sortFolders(ByName) }),
                                    SubMenuItem("By size", onClick = {}, enabled = false),
                                    SubMenuItem(
                                        "By type",
                                        onClick = { sortFolders(ByType) },
                                        enabled = true
                                    ),
                                    SubMenuItem("By date", onClick = { sortFolders(ByDate) })
                                )
                                subMenuPosition = IntOffset(position.x + 163, position.y)
                            })
                        MenuItem(text = "Line up Icons", hovered = { subMenuPosition = null })
                        MenuDivider()
                        MenuItem(
                            text = "Paste",
                            enabled = false,
                            hovered = { subMenuPosition = null })
                        MenuItem(
                            text = "Paste Shortcut",
                            enabled = false,
                            hovered = { subMenuPosition = null })
                        MenuItem(text = "Undo Copy", hovered = { subMenuPosition = null })
                        MenuDivider()
                        MenuItem(text = "New", showSubMenu = true,
                            hovered = {
                                subMenuItems = listOf(
                                    SubMenuItem("Folder", onClick = { createNewFolder(position) }),
                                    SubMenuItem("Shortcut", onClick = {}, enabled = false),
                                    SubMenuItem("Text Document", onClick = {}, enabled = false),
                                    SubMenuItem("Bitmap Image", onClick = {}, enabled = false),
                                )
                                val extraY: Int = it?.y?.toInt() ?: 0
                                subMenuPosition = IntOffset(position.x + 163, position.y + extraY)
                            })
                        MenuDivider()
                        MenuItem(text = "Properties", onClick = {
                            //Open properties
                            onDismissRequest()
                        }, hovered = { subMenuPosition = null })
                    }
                }
            }
        }
        subMenuPosition?.let {
            Popup(
                offset = it,
                onDismissRequest = { onDismissRequest() },
                alignment = Alignment.TopStart
            ) {
                SubMenu(subMenuItems)
            }
        }
    }
}
