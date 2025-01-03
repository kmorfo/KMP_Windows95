package es.rlujancreations.windows95.components.windowsbarmenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import es.rlujancreations.windows95.components.BackgroundComponent
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.ic_measure
import windows95.composeapp.generated.resources.ic_volume
import java.text.SimpleDateFormat
import java.util.Date

/**
 * Created by Raúl L.C. on 26/12/24.
 */

@Composable
fun InformationBar() {

    var currentTime by remember {
        mutableStateOf(
            SimpleDateFormat("hh:mm a").format(Date())
        )
    }

    LaunchedEffect(key1 = Unit) {
        while (true) {
            currentTime = SimpleDateFormat("hh:mm a").format(Date())
            delay(1000)
        }
    }

    BackgroundComponent(
        selected = true,
        strokeWidth = 1f,
        spacing = 1f,
        modifier = Modifier.wrapContentWidth().padding(4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(Modifier.width(4.dp))
            Image(
                modifier = Modifier.size(20.dp),
                painter = painterResource(Res.drawable.ic_measure),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
            Spacer(Modifier.width(2.dp))
            Image(
                modifier = Modifier.size(20.dp),
                painter = painterResource(Res.drawable.ic_volume),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
            Text(currentTime, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))

        }
    }
}