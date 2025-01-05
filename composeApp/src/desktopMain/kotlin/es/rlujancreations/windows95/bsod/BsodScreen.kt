package es.rlujancreations.windows95.bsod

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.compose.resources.painterResource
import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.bsod

/**
 * Created by Raúl L.C. on 5/1/25.
 */
@Composable
fun BsodScreen(onFinishBsod: () -> Unit) {
    val requester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        requester.requestFocus()
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xff0000aa))
        .onKeyEvent {
            onFinishBsod()
            true
        }
        .focusRequester(requester)
        .focusable()
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(Res.drawable.bsod),
            contentDescription = "Windows 95 Blue Screen of dead",
            contentScale = ContentScale.Fit
        )
    }
}