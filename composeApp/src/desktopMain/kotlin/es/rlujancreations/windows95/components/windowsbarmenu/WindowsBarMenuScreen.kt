package es.rlujancreations.windows95.components.windowsbarmenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.rlujancreations.windows95.components.BackgroundComponent
import es.rlujancreations.windows95.ui.backgroundComponent
import org.jetbrains.compose.resources.painterResource
import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.ic_programs

/**
 * Created by Raúl L.C. on 26/12/24.
 */
@Composable
fun WindowsBarMenuScreen(showWindowsMenu: Boolean) {
    var subBarMenuPosition by remember { mutableStateOf<Float?>(null) }

    if (showWindowsMenu) {
        Column() {
            Spacer(modifier = Modifier.weight(1f))
            Row() {
                WindowsMenu {
                    subBarMenuPosition = it
                }
                subBarMenuPosition?.let {
                    BackgroundComponent(
                        Modifier.offset(y = it.dp, x = (-4).dp).width(190.dp)
                            .background(backgroundComponent)
                    ) {
                        Column {
                            WindowsMenuItem(
                                text = "Accesories",
                                painter = painterResource(Res.drawable.ic_programs),
                                isSubMenu = true
                            ) {}
                        }
                    }
                }
            }
        }
    }
}