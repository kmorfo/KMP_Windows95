package es.rlujancreations.windows95.data.database.typeconverters

import androidx.room.TypeConverter
import org.jetbrains.compose.resources.DrawableResource
import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.ic_folder

/**
 * Created by Raúl L.C. on 2/1/25.
 */
class DrawableResourceConverter {
    @TypeConverter
    fun fromDrawableResource(drawable: DrawableResource): String {
        return drawable.toString().substringAfterLast(".")
    }

    @TypeConverter
    fun toDrawableResource(resourceName: String): DrawableResource {
        return when (resourceName) {
            "ic_folder" -> Res.drawable.ic_folder
            else -> Res.drawable.ic_folder
        }
    }
}