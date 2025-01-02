package es.rlujancreations.windows95.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

/**
 * Created by Raúl L.C. on 2/1/25.
 */
@Dao
interface FileDao {
    @Upsert
    suspend fun upsertFile(file: FileEntity)

    @Query("SELECT * FROM FileEntity WHERE path = :path")
    fun getPathFiles(path: String): Flow<List<FileEntity>>

    @Delete
    suspend fun deleteFile(file: FileEntity)
}