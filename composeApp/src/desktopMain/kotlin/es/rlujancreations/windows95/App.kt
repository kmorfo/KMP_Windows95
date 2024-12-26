package es.rlujancreations.windows95

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import es.rlujancreations.windows95.splash.SplashScreen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        var initializing by remember { mutableStateOf(false) }

        Box(modifier = Modifier.fillMaxSize()) {
            if (initializing) {
                SplashScreen { initializing = false }
            } else {
                //Windows 95
                Text("Desde escritorio")
            }
        }
    }
}