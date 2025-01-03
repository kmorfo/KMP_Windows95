package es.rlujancreations.windows95.components.rightmenu

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.rlujancreations.windows95.extensions.clickableWithoutRipple
import es.rlujancreations.windows95.ui.backgroundComponent
import es.rlujancreations.windows95.ui.disabledTextColor
import es.rlujancreations.windows95.ui.windowsBlue
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.ic_arrow

/**
 * Created by Raúl L.C. on 28/12/24.
 */
@Composable
fun MenuItem(
    text: String,
    icon: DrawableResource? = null,
    enabled: Boolean = true,
    showSubMenu: Boolean = false,
    isTextBold: Boolean = false,
    hovered: (Offset?) -> Unit,
    onClick: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val background = if (isHovered && enabled) windowsBlue else backgroundComponent
    val textColor = if (isHovered) Color.White else Color.Black

    var subMenuPosition by remember { mutableStateOf(Offset(0f, 0f)) }

    LaunchedEffect(isHovered) {
        if (isHovered) {
            hovered(subMenuPosition)
        }
    }

    Row(Modifier
        .hoverable(interactionSource)
        .fillMaxWidth()
        .padding(4.dp)
        .background(background)
        .padding(4.dp)
        .clickableWithoutRipple { if (enabled) onClick() }
        .onGloballyPositioned { layoutCoordinates ->
            val position = layoutCoordinates.positionInParent()
            subMenuPosition = Offset(
                x = position.x + layoutCoordinates.size.width.toFloat() + 10, y = position.y
            )
        }) {
        if (icon != null) {
            Icon(
                modifier = Modifier.padding(horizontal = 4.dp).size(12.dp),
                painter = painterResource(icon),
                tint = textColor,
                contentDescription = "icon $text"
            )
        }
        Spacer(Modifier.width(20.dp))
        Text(
            text,
            color = if (enabled) textColor else disabledTextColor,
            fontWeight = if (isTextBold) FontWeight.Bold else null,
            style = TextStyle(lineHeight = 0.sp)
        )
        Spacer(Modifier.weight(1f))
        if (showSubMenu) {
            Icon(
                modifier = Modifier.padding(horizontal = 4.dp).size(12.dp),
                painter = painterResource(Res.drawable.ic_arrow),
                tint = textColor,
                contentDescription = "arrow"
            )
        }
    }
}