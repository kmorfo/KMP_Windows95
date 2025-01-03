package es.rlujancreations.windows95.data.mappers

import androidx.compose.ui.geometry.Offset
import es.rlujancreations.windows95.data.database.entities.FileEntity
import es.rlujancreations.windows95.domain.model.FileModel
import kotlinx.datetime.Instant

/**
 * Created by Raúl L.C. on 2/1/25.
 */
fun FileEntity.toFileModel(): FileModel {
    return FileModel(
        id = id,
        path = path,
        type = type,
        name = name,
        position = position,
        createdDate = Instant.parse(createdDate),
        selected = false,
        icon = icon,
    )
}

fun FileModel.toEntity(): FileEntity {
    return FileEntity(
        id = id,
        path = path,
        type = type,
        name = name,
        position = position,
        createdDate = createdDate.toString(),
        icon = icon,
    )
}