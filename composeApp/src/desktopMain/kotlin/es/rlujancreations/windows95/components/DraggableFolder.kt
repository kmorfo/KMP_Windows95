package es.rlujancreations.windows95.components

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
import es.rlujancreations.windows95.domain.model.FolderModel
import es.rlujancreations.windows95.ui.windowsBlue
import org.jetbrains.compose.resources.painterResource

/**
 * Created by Raúl L.C. on 27/12/24.
 */
@Composable
fun DraggableFolder(
    folderModel: FolderModel,
    onMove: (Offset) -> Unit,
    onTapFolder: (Int) -> Unit,
    onRename: (String) -> Unit,
    onDoubleTapFolder: (FolderModel) -> Unit
) {
    var offset by remember { mutableStateOf(folderModel.position) }

    var newName by remember { mutableStateOf(folderModel.name) }
    var isEditing by remember { mutableStateOf(false) }
    var lastClickTime by remember { mutableStateOf(0L) }

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(folderModel) {
        offset = folderModel.position
    }

    Box(
        modifier = Modifier.offset(folderModel.position.x.dp, folderModel.position.y.dp)
            .width(83.dp)
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, _, _ ->
                    offset = offset.copy(x = offset.x + pan.x, y = offset.y + pan.y)
                    onMove(offset)
                }
            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        onTapFolder(folderModel.id)

                        val currentTime = System.currentTimeMillis()
                        val timeSinceLastClick = currentTime - lastClickTime
                        if (timeSinceLastClick in 300..800) {
                            isEditing = true
                        }
                        lastClickTime = currentTime
                    },
                    onPress = { onTapFolder(folderModel.id) },
                    onDoubleTap = { onDoubleTapFolder(folderModel) }
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
                    painter = painterResource(folderModel.icon),
                    contentDescription = "folder"
                )
                if (folderModel.selected) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(folderModel.icon),
                        contentDescription = "folder",
                        tint = windowsBlue.copy(alpha = 0.4f)
                    )
                }
            }
            if (isEditing) {
                BasicTextField(
                    value = newName,
                    onValueChange = { newName = it },
                    modifier = Modifier.focusRequester(focusRequester).background(White),
                    singleLine = true,
                    maxLines = 1
                )

                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }

            } else {
                Text(
                    folderModel.name,
                    color = White,
                    fontSize = 13.sp,
                    maxLines = 2,
                    style = TextStyle(lineHeight = 0.sp),
                    modifier = Modifier.background(if (folderModel.selected) windowsBlue else Color.Transparent),
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row {
                Spacer(Modifier.weight(1f))
            }
        }
        if (isEditing) {
            Box(Modifier.fillMaxSize().pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        isEditing = false
                        onRename(newName)
                    }
                )
            })
        }
    }
}