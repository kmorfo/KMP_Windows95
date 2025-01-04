package es.rlujancreations.windows95.domain

import es.rlujancreations.windows95.domain.model.FileModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by Raúl L.C. on 3/1/25.
 */

interface FileRepository {
    fun getPathFiles(path: String): Flow<List<FileModel>>
    suspend fun upsertFile(file: FileModel)
    suspend fun deleteFile(file: FileModel)
    suspend fun deleteChildFiles(path: String)
}