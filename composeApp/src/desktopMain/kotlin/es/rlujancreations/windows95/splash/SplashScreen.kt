package es.rlujancreations.windows95.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import es.rlujancreations.windows95.helper.SoundManager
import org.jetbrains.compose.resources.painterResource
import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.splash

/**
 * Created by Raúl L.C. on 26/12/24.
 */
@Composable
fun SplashScreen(onSoundFinished: () -> Unit) {
    val soundManager = remember { SoundManager() }

    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(Res.drawable.splash),
        contentDescription = "Windows 95 Splash Screen",
        contentScale = ContentScale.Crop
    )
    soundManager.playSound("splashsound.wav") {
        onSoundFinished()
    }
}