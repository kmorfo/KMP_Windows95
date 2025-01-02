package es.rlujancreations.windows95.data.mappers

import es.rlujancreations.windows95.data.database.entities.FileEntity
import es.rlujancreations.windows95.domain.model.FileModel
import kotlinx.datetime.Instant
import androidx.compose.ui.geometry.Offset
import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.ic_folder

/**
 * Created by Raúl L.C. on 2/1/25.
 */
fun FileEntity.toFileModel(): FileModel {
    return FileModel(
        id = id,
        path = path,
        type = type,
        name = name,
        position = Offset(0f, 0f),
        createdDate = Instant.parse(createdDate),
        selected = selected,
        icon = Res.drawable.ic_folder,
    )
}

fun FileModel.toEntity(): FileEntity {
    return FileEntity(
        id = id,
        path = path,
        type = type,
        name = name,
        position = "position",
        createdDate = createdDate.toString(),
        selected = selected,
        icon = "Res.drawable.ic_folder",
    )
}