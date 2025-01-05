package es.rlujancreations.windows95

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import es.rlujancreations.windows95.bsod.BsodScreen
import es.rlujancreations.windows95.desktop.Desktop
import es.rlujancreations.windows95.splash.SplashScreen
import es.rlujancreations.windows95.ui.Windows95Typography
import es.rlujancreations.windows95.ui.background
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun App() {
    MaterialTheme(typography = Windows95Typography()) {
        var isInitializing by remember { mutableStateOf(false) }
        var showBSOD by remember { mutableStateOf(false) }

        val nextBSODInterval = remember { Random.nextInt(5000, 99999) }

        LaunchedEffect(Unit) {
            delay(nextBSODInterval.toLong())
            showBSOD = true
        }

        Box(modifier = Modifier.fillMaxSize().background(background)) {
            when {
                isInitializing -> {
                    SplashScreen { isInitializing = false }
                }
                showBSOD -> {
                    BsodScreen { showBSOD = false }
                }
                else -> {
                    Desktop()
                }
            }
        }
    }
}
