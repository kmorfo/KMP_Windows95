package es.rlujancreations.windows95.data.database

import es.rlujancreations.windows95.data.database.dao.FileDao
import es.rlujancreations.windows95.data.mappers.toEntity
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

    override suspend fun upsertFile(file: FileModel) {
        return dao.upsertFile(file.toEntity())
    }

    override suspend fun deleteFile(file: FileModel) {
        return dao.deleteFile(file.toEntity())
    }

    override suspend fun deleteChildFiles(path: String) {
        dao.deleteChildFiles(path)
    }
}