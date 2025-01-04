package es.rlujancreations.windows95.components.window

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rlujancreations.windows95.domain.FileRepository
import es.rlujancreations.windows95.helper.DefaultFoldersProvider
import es.rlujancreations.windows95.model.WindowModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

/**
 * Created by Raúl L.C. on 4/1/25.
 */
class DraggableWindowViewModel(
    private val fileRepository: FileRepository
) : ViewModel() {
    private var observeFilesJob: Job? = null

    private val _state = MutableStateFlow(DraggableWindowState())
    val state = _state
        .onStart {
            observeFiles()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _state.value
        )

    fun setWindow(window: WindowModel) {
        _state.update {
            it.copy(window = window)
        }
        observeFiles()
    }

    fun stopWindow() {
        _state.update {
            it.copy(window = null , files = emptyList())
        }
        observeFiles()
    }

    private fun observeFiles() {
        observeFilesJob?.cancel()
        if (_state.value.window != null) {
            observeFilesJob =
                fileRepository.getPathFiles("${_state.value.window?.id}").onEach { files ->
                    if (files.isNotEmpty()) {
                        _state.update { it.copy(files = files) }
                        println(files.size)
                    } else {
                        DefaultFoldersProvider.default.map { fileRepository.upsertFile(it) }
                    }
                }.launchIn(viewModelScope)
        }
    }
}