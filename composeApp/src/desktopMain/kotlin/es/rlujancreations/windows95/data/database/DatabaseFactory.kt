package es.rlujancreations.windows95.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

/**
 * Created by Raúl L.C. on 2/1/25.
 */
class DatabaseFactory {
    fun create(): RoomDatabase.Builder<WindowsDatabase> {
        val os = System.getProperty("os.name").lowercase()
        val userHome = System.getProperty("user.home")
        val appDataDir = when {
            os.contains("win") -> File(System.getenv("APPDATA"), "Bookpedia")
            os.contains("mac") -> File(userHome, "Library/Application Support/Bookpedia")
            else -> File(userHome, ".local/share/Bookpedia")
        }

        if (!appDataDir.exists()) {
            appDataDir.mkdirs()
        }

        val dbFile = File(appDataDir, WindowsDatabase.DB_NAME)
        return Room.databaseBuilder(dbFile.absolutePath)
    }
}