package es.rlujancreations.windows95.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * Created by Raúl L.C. on 2/1/25.
 */
@Database(
    entities = [FileEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    OffsetStringTypeConverter::class
)
abstract class WindowsDatabase : RoomDatabase() {
    abstract fun fileDao(): FileDao

    companion object {
        const val DB_NAME = "windows_database.db"
    }

}