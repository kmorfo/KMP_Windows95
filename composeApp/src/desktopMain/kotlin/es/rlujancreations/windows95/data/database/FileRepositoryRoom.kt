package es.rlujancreations.windows95.data.database

import es.rlujancreations.windows95.data.database.dao.FileDao
import es.rlujancreations.windows95.data.database.entities.FileEntity
import es.rlujancreations.windows95.data.mappers.toFileModel
import es.rlujancreations.windows95.domain.FileRepository
import es.rlujancreations.windows95.domain.model.FileModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by Raúl L.C. on 3/1/25.
 */
class FileRepositoryRoom(
    private val dao: FileDao
) : FileRepository {
    override fun getPathFiles(path: String): Flow<List<FileModel>> {
        return dao.getPathFiles(path).map {
            it.map { file -> file.toFileModel() }
        }
    }

    override suspend fun upsertFile(file: FileEntity) {
        return dao.upsertFile(file)
    }

    override suspend fun deleteFile(file: FileEntity) {
        return dao.deleteFile(file)
    }
}