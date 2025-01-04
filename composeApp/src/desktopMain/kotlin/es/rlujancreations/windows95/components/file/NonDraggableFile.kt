package es.rlujancreations.windows95.components.file

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.rlujancreations.windows95.domain.model.FileModel
import es.rlujancreations.windows95.extensions.onRightClick
import es.rlujancreations.windows95.ui.getIcon
import es.rlujancreations.windows95.ui.windowsBlue
import org.jetbrains.compose.resources.painterResource

/**
 * Created by Raúl L.C. on 27/12/24.
 */
@Composable
fun NonDraggableFile(
    file: FileModel,
    onTapFile: (Int) -> Unit,
    onRightClick: (FileModel) -> Unit,
    onDoubleTapFile: (FileModel) -> Unit
) {

    Box(
        modifier = Modifier
            .width(83.dp)
            .onRightClick { onRightClick(file) }
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        onTapFile(file.id)
                    },
                    onPress = { onTapFile(file.id) },
                    onDoubleTap = { onDoubleTapFile(file) }
                )
            }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.width(50.dp).height(intrinsicSize = IntrinsicSize.Min)) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(getIcon(file.icon)),
                    contentDescription = "file icon"
                )
                if (file.selected) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(getIcon(file.icon)),
                        contentDescription = "file icon",
                        tint = windowsBlue.copy(alpha = 0.4f)
                    )
                }
            }
            Text(
                file.name,
                color = Color.Black,
                fontSize = 13.sp,
                maxLines = 2,
                style = TextStyle(lineHeight = 0.sp),
                modifier = Modifier.background(if (file.selected) windowsBlue else Color.Transparent),
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )
        }
        Row {
            Spacer(Modifier.weight(1f))
        }
    }

}
