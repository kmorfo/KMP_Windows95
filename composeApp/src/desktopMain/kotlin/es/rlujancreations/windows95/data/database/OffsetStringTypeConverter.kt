package es.rlujancreations.windows95.data.database

import androidx.compose.ui.geometry.Offset
import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Created by Raúl L.C. on 2/1/25.
 */
object OffsetStringTypeConverter {
    @TypeConverter
    fun fromString(value: String): Offset {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromList(offset: Offset): String {
        return Json.encodeToString(offset)
    }
}