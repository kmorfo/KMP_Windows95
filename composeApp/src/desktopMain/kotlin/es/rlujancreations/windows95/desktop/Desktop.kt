package es.rlujancreations.windows95.desktop

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import es.rlujancreations.windows95.components.windowsbarmenu.WindowsBar
import es.rlujancreations.windows95.components.windowsbarmenu.WindowsBarMenuScreen
import es.rlujancreations.windows95.extensions.clickableWithoutRipple

/**
 * Created by Raúl L.C. on 26/12/24.
 */
@Composable
fun Desktop() {
    var showWindowsMenu by remember { mutableStateOf(false) }

    Column {
        Box(
            modifier = Modifier.fillMaxWidth().weight(1f)
                .clickableWithoutRipple { showWindowsMenu = false }) {
            WindowsBarMenuScreen(showWindowsMenu)
        }
        WindowsBar { showWindowsMenu = !showWindowsMenu }
    }
}