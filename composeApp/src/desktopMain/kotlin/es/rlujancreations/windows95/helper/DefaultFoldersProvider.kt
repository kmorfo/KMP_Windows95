package es.rlujancreations.windows95.helper

import androidx.compose.ui.geometry.Offset
import es.rlujancreations.windows95.domain.model.FolderModel
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
        FolderModel(
            id = 0,
            "My Computer",
            position = Offset(x = 10f, y = 10f),
            icon = Res.drawable.ic_my_computer
        ),
        FolderModel(
            id = 1,
            "Network Neighborhood",
            position = Offset(x = 10f, y = 110f),
            icon = Res.drawable.ic_network
        ),
        FolderModel(
            id = 2,
            "Internet Explorer",
            position = Offset(x = 10f, y = 210f),
            icon = Res.drawable.ic_ie
        ),
        FolderModel(
            id = 3,
            "Microsoft Age of Empires II",
            position = Offset(x = 10f, y = 310f),
            icon = Res.drawable.ic_aoe
        ),
        FolderModel(
            id = 4,
            "Recycle Bin",
            position = Offset(x = 1100f, y = 750f),
            icon = Res.drawable.ic_bin
        ),
    )
}