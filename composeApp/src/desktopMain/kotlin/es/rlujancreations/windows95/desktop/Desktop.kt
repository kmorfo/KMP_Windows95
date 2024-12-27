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
import androidx.compose.ui.geometry.Offset
import es.rlujancreations.windows95.components.DraggableFolder
import es.rlujancreations.windows95.components.windowsbarmenu.WindowsBar
import es.rlujancreations.windows95.components.windowsbarmenu.WindowsBarMenuScreen
import es.rlujancreations.windows95.extensions.clickableWithoutRipple
import es.rlujancreations.windows95.model.FolderModel

/**
 * Created by Raúl L.C. on 26/12/24.
 */
@Composable
fun Desktop() {
    var showWindowsMenu by remember { mutableStateOf(false) }
    val fakeFolder = FolderModel(0, "RLujanCreations", position = Offset(0f, 0f))
    var folders by remember { mutableStateOf(listOf<FolderModel>(fakeFolder)) }

    Column {
        Box(
            modifier = Modifier.fillMaxWidth().weight(1f)
                .clickableWithoutRipple {
                    showWindowsMenu = false
                    folders = clearFolders(folders)
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
                    onDoubleTapFolder = { folderModel ->
                        println(folderModel.name)
                    }
                )
            }

            WindowsBarMenuScreen(showWindowsMenu = showWindowsMenu)
        }
        WindowsBar { showWindowsMenu = !showWindowsMenu }
    }
}

fun clearFolders(folders: List<FolderModel>): List<FolderModel> {
    return folders.map { it.copy(selected = false) }
}
