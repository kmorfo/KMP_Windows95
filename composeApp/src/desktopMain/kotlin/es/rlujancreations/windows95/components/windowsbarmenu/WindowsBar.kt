package es.rlujancreations.windows95.components.windowsbarmenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.rlujancreations.windows95.components.WindowsButton
import es.rlujancreations.windows95.ui.backgroundComponent
import org.jetbrains.compose.resources.painterResource
import windows95.composeapp.generated.resources.Res
import windows95.composeapp.generated.resources.winlogo

/**
 * Created by Raúl L.C. on 26/12/24.
 */
@Composable
fun WindowsBar(
    onWindowsButtonSelected: () -> Unit
) {
    Column {
        Box(Modifier.height(1.dp).fillMaxWidth().background(Color.White))
        Row(
            modifier = Modifier.height(40.dp).fillMaxWidth().background(backgroundComponent),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(Modifier.width(6.dp))
            WindowsButton(Modifier.height(34.dp).width(95.dp),
                onClick = { onWindowsButtonSelected() }) {
                Row(
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(Res.drawable.winlogo),
                        contentDescription = "Windows Logo",
                        contentScale = ContentScale.FillWidth
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        "Start",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 4.dp),
                        color = Color.Black
                    )
                }
            }
            Row(modifier = Modifier.weight(1f)) {

            }
            InformationBar()
        }
    }
}