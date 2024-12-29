package es.rlujancreations.windows95.helper

import androidx.compose.ui.geometry.Offset
import es.rlujancreations.windows95.model.FolderModel
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
        FolderModel(0, "My Computer", Offset(x = 10f, y = 10f), icon = Res.drawable.ic_my_computer),
        FolderModel(
            1, "Network Neighborhood", Offset(x = 10f, y = 110f), icon = Res.drawable.ic_network
        ),
        FolderModel(
            2, "Internet Explorer", Offset(x = 10f, y = 210f), icon = Res.drawable.ic_ie
        ),
        FolderModel(
            3,
            "Microsoft Age of Empires II",
            Offset(x = 10f, y = 310f),
            icon = Res.drawable.ic_aoe
        ),
        FolderModel(
            4, "Recycle Bin", Offset(x = 1100f, y = 750f), icon = Res.drawable.ic_bin
        ),
    )
}