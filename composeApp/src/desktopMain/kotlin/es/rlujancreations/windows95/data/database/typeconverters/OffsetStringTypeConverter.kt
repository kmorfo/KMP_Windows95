package es.rlujancreations.windows95.data.database.typeconverters

import androidx.compose.ui.geometry.Offset
import androidx.room.TypeConverter

/**
 * Created by Raúl L.C. on 2/1/25.
 */
object OffsetStringTypeConverter {
    @TypeConverter
    fun fromString(value: String): Offset {
        val (x, y) = value.split(",").map { it.toFloat() }
        return Offset(x, y)
    }

    @TypeConverter
    fun fromList(offset: Offset): String {
        return "${offset.x},${offset.y}"
    }
}