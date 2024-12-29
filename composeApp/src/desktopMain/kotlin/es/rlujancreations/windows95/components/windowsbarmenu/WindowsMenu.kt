package es.rlujancreations.windows95.components.windowsbarmenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.rlujancreations.windows95.components.BackgroundComponent
import es.rlujancreations.windows95.components.rightmenu.MenuDivider
import es.rlujancreations.windows95.extensions.rotateVertically
import es.rlujancreations.windows95.ui.backgroundComponent
import es.rlujancreations.windows95.ui.windowsBarTextBackground
import org.jetbrains.compose.resources.painterResource
import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.ic_documents
import windows95.composeapp.generated.resources.ic_find
import windows95.composeapp.generated.resources.ic_help
import windows95.composeapp.generated.resources.ic_programs
import windows95.composeapp.generated.resources.ic_run
import windows95.composeapp.generated.resources.ic_settings
import windows95.composeapp.generated.resources.ic_shutdown

/**
 * Created by Raúl L.C. on 26/12/24.
 */
@Composable
fun WindowsMenu(showSubMenu: (Float?) -> Unit) {
    BackgroundComponent(Modifier.height(340.dp)) {
        Column {
            Row {
                Column(modifier = Modifier.padding(bottom = 4.dp)) {
                    Spacer(Modifier.weight(1f))
                    Box(
                        Modifier.padding(start = 4.dp, top = 4.dp).fillMaxHeight()
                            .background(windowsBarTextBackground).padding(2.dp),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        Column {
                            Text(
                                "95",
                                modifier = Modifier.rotateVertically(false),
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Thin,
                                fontFamily = androidx.compose.ui.text.font.FontFamily.Default,
                                color = Color.White
                            )
                            Text(
                                "Windows",
                                modifier = Modifier.rotateVertically(false),
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = androidx.compose.ui.text.font.FontFamily.Default,
                                color = backgroundComponent
                            )
                        }
                    }
                }
                Column(Modifier.fillMaxHeight().width(200.dp)) {
                    WindowsMenuItem(
                        text = "Programs",
                        painter = painterResource(Res.drawable.ic_programs),
                        expandable = true
                    ) {
                        showSubMenu(it)
                    }
                    WindowsMenuItem(
                        text = "Documents",
                        painter = painterResource(Res.drawable.ic_documents),
                        expandable = true
                    ) {
                        showSubMenu(it)
                    }
                    WindowsMenuItem(
                        text = "Settings",
                        painter = painterResource(Res.drawable.ic_settings),
                        expandable = true
                    ) {
                        showSubMenu(it)
                    }
                    WindowsMenuItem(
                        text = "Find",
                        painter = painterResource(Res.drawable.ic_find),
                        expandable = true
                    ) {
                        showSubMenu(it)
                    }
                    WindowsMenuItem(
                        text = "Help", painter = painterResource(Res.drawable.ic_help)
                    ) {
                        showSubMenu(it)
                    }
                    WindowsMenuItem(
                        text = "Run...", painter = painterResource(Res.drawable.ic_run)
                    ) {
                        showSubMenu(it)
                    }
                    MenuDivider()
                    WindowsMenuItem(
                        text = "Shut Down...", painter = painterResource(Res.drawable.ic_shutdown)
                    ) {
                        showSubMenu(it)
                    }
                }
            }
        }
    }
}