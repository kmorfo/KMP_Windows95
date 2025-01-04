@file:OptIn(ExperimentalFoundationApi::class)

package es.rlujancreations.windows95.components.window

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.PointerMatcher
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.onDrag
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.onClick
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerButton
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import es.rlujancreations.windows95.components.BackgroundComponent
import es.rlujancreations.windows95.components.file.DraggableFile
import es.rlujancreations.windows95.components.file.NonDraggableFile
import es.rlujancreations.windows95.domain.model.FileModel
import es.rlujancreations.windows95.extensions.onRightClick
import es.rlujancreations.windows95.model.WindowModel
import org.koin.compose.viewmodel.koinViewModel

/**
 * Created by Raúl L.C. on 28/12/24.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DraggableWindow(
    windowViewModel: DraggableWindowViewModel = koinViewModel(),
    windowModel: WindowModel,
    onMove: (Offset) -> Unit,
    onClose: () -> Unit,
    onMinimize: () -> Unit,
    onExpand: () -> Unit,
    onClick: () -> Unit,
    onRightClick: (WindowModel) -> Unit,
    hasStatusBar: Boolean = true
) {
    LaunchedEffect(windowModel.id) {
        windowViewModel.setWindow(windowModel)
    }

    val state by windowViewModel.state.collectAsState()

    var currentOffSet by remember { mutableStateOf(windowModel.position) }
    val density = LocalDensity.current

    var prevPosition by remember { mutableStateOf(windowModel.position) }

    BoxWithConstraints {
        val containerWidthPx = with(density) { maxWidth.toPx() }
        val containerHeightPx = with(density) { maxHeight.toPx() }

        val windowWidthPx =
            if (windowModel.expanded) containerWidthPx else with(density) { windowModel.size.width.toPx() }
        val windowHeightPx =
            if (windowModel.expanded) containerHeightPx else with(density) { windowModel.size.height.toPx() }

        LaunchedEffect(windowModel.expanded) {
            currentOffSet = if (windowModel.expanded) {
                Offset(0f, 0f)
            } else {
                prevPosition
            }
            onMove(currentOffSet)
        }

        BackgroundComponent(Modifier.then(
            if (windowModel.expanded) Modifier.fillMaxSize() else Modifier.size(windowModel.size)
        ).offset {
            IntOffset(currentOffSet.x.toInt(), currentOffSet.y.toInt())
        }.onDrag(
            matcher = PointerMatcher.mouse(PointerButton.Primary),
            onDrag = { offset ->
                if (!windowModel.expanded) {
                    val newX = (currentOffSet.x + offset.x).coerceIn(
                        0f, containerWidthPx - windowWidthPx
                    )
                    val newY = (currentOffSet.y + offset.y).coerceIn(
                        0f, containerHeightPx - windowHeightPx
                    )
                    val newOffSet = Offset(newX, newY)
                    currentOffSet = newOffSet
                    prevPosition = newOffSet
                    onMove(newOffSet)
                }
            }
        ).onClick { onClick() }
        ) {
            Column {
                WindowToolbar(Modifier.padding(4.dp),
                    windowModel.title,
                    icon = windowModel.icon,
                    windowModel.selected,
                    onMinimize = { onMinimize() },
                    onExpand = { onExpand() },
                    onClose = {
                        windowViewModel.stopWindow()
                        onClose()
                    })
                Row {
                    Spacer(Modifier.width(10.dp))
                    Text("File")
                    Spacer(Modifier.width(10.dp))
                    Text("Edit")
                    Spacer(Modifier.width(10.dp))
                    Text("View")
                    Spacer(Modifier.width(10.dp))
                    Text("Help")
                }
                BackgroundComponent(
                    Modifier.fillMaxSize()
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp, top = 4.dp),
                    selected = true
                ) {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .onRightClick { onRightClick(windowModel) },
                        contentAlignment = Alignment.BottomStart
                    ) {
                        FlowRow(Modifier.fillMaxSize()) {
                            state.files.forEach { file ->
                                NonDraggableFile(
                                    file = file,
                                    onTapFile = { },
                                    onDoubleTapFile = {},
                                    onRightClick = {}
                                )
                            }
                        }
                        if (hasStatusBar) {
                            WindowStatusBar(items = state.files.size)
                        }
                    }
                }
            }
        }
    }
}
