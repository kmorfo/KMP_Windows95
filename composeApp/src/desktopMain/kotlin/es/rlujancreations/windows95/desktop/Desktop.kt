package es.rlujancreations.windows95.desktop

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.rlujancreations.windows95.components.windowsbarmenu.WindowsBar

/**
 * Created by Raúl L.C. on 26/12/24.
 */
@Composable
fun Desktop() {
    Column {
        Box(modifier = Modifier.fillMaxWidth().weight(1f))
        WindowsBar()
    }
}