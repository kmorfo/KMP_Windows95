package es.rlujancreations.windows95.components.window

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import es.rlujancreations.windows95.components.WindowsButton
import es.rlujancreations.windows95.ui.backgroundComponent
import es.rlujancreations.windows95.ui.disabledTextColor
import es.rlujancreations.windows95.ui.windowsBarTextBackground
import es.rlujancreations.windows95.ui.windowsBlue
import org.jetbrains.compose.resources.painterResource
import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.ic_close
import windows95.composeapp.generated.resources.ic_expand
import windows95.composeapp.generated.resources.ic_folder_open
import windows95.composeapp.generated.resources.ic_minimize

/**
 * Created by Raúl L.C. on 28/12/24.
 */
@Composable
fun WindowToolbar(
    modifier: Modifier,
    title: String,
    selected: Boolean,
    onMinimize: (() -> Unit)? = null,
    onExpand: (() -> Unit)? = null,
    onClose: (() -> Unit)? = null,
) {
    val textColor = if (selected) White else backgroundComponent
    val backgroundColor = if (selected) windowsBlue else windowsBarTextBackground

    Row(
        modifier.fillMaxWidth().height(24.dp).background(backgroundColor),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.width(1.dp))
        Image(
            painter = painterResource(Res.drawable.ic_folder_open),
            contentDescription = null,
            modifier = Modifier.size(19.dp)
        )
        Spacer(Modifier.width(4.dp))
        Text(title, color = textColor)
        Spacer(Modifier.weight(1f))
        if (onMinimize != null) {
            WindowsButton(Modifier.size(20.dp), onClick = { onMinimize() }) {
                Box(Modifier.size(20.dp), contentAlignment = Alignment.Center) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_minimize),
                        contentDescription = "minimize",
                        tint = Color.Black
                    )
                }
            }
        }
        Spacer(Modifier.width(2.dp))
        if (onExpand != null) {
            WindowsButton(Modifier.size(20.dp), onClick = { onExpand() }) {
                Box(Modifier.size(20.dp), contentAlignment = Alignment.Center) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_expand),
                        contentDescription = "expand",
                        tint = Color.Black
                    )
                }
            }
        }
        Spacer(Modifier.width(4.dp))
        if (onClose != null) {
            WindowsButton(Modifier.size(20.dp), onClick = { onClose() }) {
                Box(Modifier.size(20.dp), contentAlignment = Alignment.Center) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_close),
                        contentDescription = "close",
                        tint = Color.Black
                    )
                }
            }
        }
        Spacer(Modifier.width(4.dp))
    }
}
