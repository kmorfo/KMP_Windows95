package es.rlujancreations.windows95.helper

import androidx.compose.ui.geometry.Offset
import es.rlujancreations.windows95.domain.model.FileModel
import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.ic_aoe
import windows95.composeapp.generated.resources.ic_bin
import windows95.composeapp.generated.resources.ic_ie
import windows95.composeapp.generated.resources.ic_my_computer
import windows95.composeapp.generated.resources.ic_network

/**
 * Created by Raúl L.C. on 29/12/24.
 */
object DefaultFoldersProvider {
    val default = listOf(
        FileModel(
            id = 1,
            path = "Desktop",
            name = "My Computer",
            position = Offset(x = 10f, y = 10f),
            icon = "ic_my_computer"
        ),
        FileModel(
            id = 2,
            path = "Desktop",
            name = "Network Neighborhood",
            position = Offset(x = 10f, y = 110f),
            icon = "ic_network"
        ),
        FileModel(
            id = 3,
            path = "Desktop",
            name = "Internet Explorer",
            position = Offset(x = 10f, y = 210f),
            icon = "ic_ie"
        ),
        FileModel(
            id = 4,
            path = "Desktop",
            name = "Microsoft Age of Empires II",
            position = Offset(x = 10f, y = 310f),
            icon = "ic_aoe"
        ),
        FileModel(
            id = 5,
            path = "Desktop",
            name = "Recycle Bin",
            position = Offset(x = 1100f, y = 750f),
            icon = "ic_bin"
        ),
    )
}