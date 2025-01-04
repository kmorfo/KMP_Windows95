package es.rlujancreations.windows95.components.window

import es.rlujancreations.windows95.domain.model.FileModel
import es.rlujancreations.windows95.model.WindowModel

/**
 * Created by Raúl L.C. on 4/1/25.
 */
data class DraggableWindowState(
    val window: WindowModel? = null,
    val files: List<FileModel> = emptyList(),
)