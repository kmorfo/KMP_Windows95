package es.rlujancreations.windows95.data.database.entities

import androidx.compose.ui.geometry.Offset
import androidx.room.Entity
import androidx.room.PrimaryKey
import es.rlujancreations.windows95.domain.model.FileType

/**
 * Created by Raúl L.C. on 2/1/25.
 */
@Entity
data class FileEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val path: String,
    val type: FileType = FileType.Folder,
    val name: String = "New folder",
    val position: Offset,
    val createdDate: String,
    val icon: String,
)