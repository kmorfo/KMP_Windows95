package es.rlujancreations.windows95.ui

import org.jetbrains.compose.resources.DrawableResource
import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.ic_aoe
import windows95.composeapp.generated.resources.ic_bin
import windows95.composeapp.generated.resources.ic_bin_full
import windows95.composeapp.generated.resources.ic_folder
import windows95.composeapp.generated.resources.ic_folder_open
import windows95.composeapp.generated.resources.ic_ie
import windows95.composeapp.generated.resources.ic_minesweeper
import windows95.composeapp.generated.resources.ic_my_computer
import windows95.composeapp.generated.resources.ic_network
import windows95.composeapp.generated.resources.ic_volume

/**
 * Created by Raúl L.C. on 3/1/25.
 */
fun getIcon(iconReference: String): DrawableResource {
    return when (iconReference) {
        "ic_folder" -> Res.drawable.ic_folder
        "ic_folder_open" -> Res.drawable.ic_folder_open
        "ic_my_computer" -> Res.drawable.ic_my_computer
        "ic_network" -> Res.drawable.ic_network
        "ic_ie" -> Res.drawable.ic_ie
        "ic_bin" -> Res.drawable.ic_bin
        "ic_bin_full" -> Res.drawable.ic_bin_full
        "ic_aoe" -> Res.drawable.ic_aoe
        "ic_volume" -> Res.drawable.ic_volume
        "ic_minesweeper" -> Res.drawable.ic_minesweeper
        else -> Res.drawable.ic_folder
    }
}