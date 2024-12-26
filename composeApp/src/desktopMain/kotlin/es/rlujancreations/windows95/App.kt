package es.rlujancreations.windows95

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import es.rlujancreations.windows95.desktop.Desktop
import es.rlujancreations.windows95.splash.SplashScreen
import es.rlujancreations.windows95.ui.background
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var initializing by remember { mutableStateOf(false) }

        Box(modifier = Modifier.fillMaxSize().background(background)) {
            if (initializing) {
                SplashScreen { initializing = false }
            } else {
                Desktop()
            }
        }
    }
}