package es.rlujancreations.windows95.desktop

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rlujancreations.windows95.data.database.dao.FileDao
import es.rlujancreations.windows95.data.mappers.toEntity
import es.rlujancreations.windows95.helper.DefaultFoldersProvider
import es.rlujancreations.windows95.domain.model.FileSortType
import es.rlujancreations.windows95.domain.model.FileModel
import es.rlujancreations.windows95.model.WindowModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Created by Raúl L.C. on 30/12/24.
 */
class DesktopViewModel(
    private val fileDao: FileDao
) : ViewModel() {
    private var observeFilesJob: Job? = null

    private val _state = MutableStateFlow(DesktopState())
    val state = _state
        .onStart {
            observeFiles()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _state.value
        )

    private fun observeFiles() {
        observeFilesJob?.cancel()
        observeFilesJob = fileDao.getPathFiles("Desktop").onEach { file ->
            println("File ${file.size}")

        }.launchIn(viewModelScope)

        _state.update { it.copy(folders = DefaultFoldersProvider.default) }
    }

    fun onAction(action: DesktopAction) {
        when (action) {
            DesktopAction.OnClearFiles -> {
                clearFolders()
            }

            DesktopAction.OnUnselectWindows -> {
                unselectWindows()
            }

            is DesktopAction.OnFileMove -> {
                _state.update {
                    it.copy(
                        folders = it.folders.map { folder ->
                            if (folder.id == action.fileId) {
                                folder.copy(position = action.offset)
                            } else {
                                folder
                            }
                        }
                    )
                }
            }

            DesktopAction.OnClearWindows -> {
                _state.update { it.copy(showWindowsMenu = false, showRightClickMenu = false) }
                clearFolders()
                unselectWindows()
            }

            is DesktopAction.OnClickRightClick -> {
                _state.update {
                    it.copy(
                        rightClickPosition = action.position,
                        showRightClickMenu = true
                    )
                }
            }

            is DesktopAction.OnTabFile -> {
                _state.update {
                    it.copy(
                        folders = it.folders.map { file -> file.copy(selected = action.fileId == file.id) }
                    )
                }
            }

            DesktopAction.OnDismissRightClickMenu -> {
                _state.update {
                    it.copy(showRightClickMenu = false)
                }
            }

            is DesktopAction.OnRenameFile -> {
                _state.update {
                    it.copy(
                        folders = it.folders.map { folder ->
                            if (folder.id == action.fileId) folder.copy(name = action.newName) else folder
                        }
                    )
                }
            }

            is DesktopAction.OnCreateFolder -> {
                val newFolder = FileModel(
                    id = _state.value.folders.size + 1,
                    position = Offset(action.position.x, action.position.y)
                )
                viewModelScope.launch {
                    fileDao.upsertFile(newFolder.toEntity())
                }

                _state.update {
                    it.copy(
                        folders = it.folders + newFolder,
                        showRightClickMenu = false
                    )
                }
            }

            is DesktopAction.OnSortFiles -> {
                _state.update {
                    it.copy(
                        showRightClickMenu = false,
                        folders = when (action.sortType) {
                            FileSortType.ByName -> it.folders.sortedBy { it.name }
                            FileSortType.ByDate -> it.folders.sortedByDescending { it.createdDate }
                        }.mapIndexed { pos: Int, folder: FileModel ->
                            folder.copy(position = Offset(y = (pos * 75).toFloat(), x = 0f))
                        }
                    )
                }
            }

            is DesktopAction.OnClickMinimizedWindow -> {
                _state.update {
                    it.copy(
                        windows = it.windows.map { window ->
                            if (action.windowId == window.id) window.copy(
                                minimized = !window.minimized,
                                selected = true
                            ) else window.copy(selected = false)
                        }
                    )
                }
            }

            DesktopAction.OnToggleWindowsMenu -> {
                _state.update {
                    it.copy(showWindowsMenu = !it.showWindowsMenu)
                }
            }

            is DesktopAction.OnDoubleTabFile -> {
                val selectedWindow =
                    state.value.windows.find { window -> window.id == action.file.id }
                if (state.value.windows.any { window -> window.id == action.file.id }) {
                    _state.update {
                        it.copy(
                            windows = it.windows.map { window ->
                                if (window.id == action.file.id) window.copy(selected = true)
                                else window.copy(selected = false)
                            }
                        )
                    }
                } else {
                    val extraPosition = _state.value.windows.size * 10
                    unselectWindows()
                    val newWindow = WindowModel(
                        id = action.file.id,
                        title = action.file.name,
                        icon = action.file.icon,
                        selected = true,
                        position = Offset(100f + extraPosition, 100f + extraPosition)
                    )
                    _state.update {
                        it.copy(
                            windows = it.windows + newWindow
                        )
                    }
                }
            }

            is DesktopAction.OnWindowClick -> {
                _state.update {
                    it.copy(
                        windows = it.windows.map { window ->
                            if (window.id == action.windowId) window.copy(selected = true)
                            else window.copy(selected = false)
                        }
                    )
                }
            }

            is DesktopAction.OnWindowMove -> {
                _state.update {
                    it.copy(
                        windows = it.windows.map { window ->
                            if (window.id == action.windowId) window.copy(position = action.offset)
                            else window
                        }
                    )
                }
            }

            is DesktopAction.OnWindowClose -> {
                _state.update {
                    it.copy(
                        windows = it.windows.filter { window -> window.id != action.windowId }
                    )
                }
            }

            is DesktopAction.OnWindowExpand -> {
                _state.update {
                    it.copy(
                        windows = it.windows.map { window ->
                            if (window.id == action.windowId)
                                window.copy(expanded = !window.expanded)
                            else window
                        }
                    )
                }
            }

            is DesktopAction.OnWindowMinimize -> {
                _state.update {
                    it.copy(
                        windows = it.windows.map { window ->
                            if (window.id == action.windowId) window.copy(
                                minimized = true,
                                selected = false
                            ) else window
                        }
                    )
                }
            }
        }
    }

    private fun unselectWindows() {
        _state.update {
            it.copy(windows = _state.value.windows.map { windows ->
                windows.copy(selected = false)
            })
        }
    }

    private fun clearFolders() {
        _state.update {
            it.copy(folders = _state.value.folders.map { folder ->
                folder.copy(selected = false)
            })
        }
    }
}
