package es.rlujancreations.windows95.helper

import java.io.File
import java.net.URL
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip
import javax.sound.sampled.LineEvent

/**
 * Created by Raúl L.C. on 26/12/24.
 */

class SoundManager {

    fun playSound(soundResource: String, onSoundFinished: () -> Unit) {
        val classLoader = this::class.java.classLoader
        val resource: URL? = classLoader.getResource(soundResource)

        if (resource != null) {
            val file = File(resource.toURI())
            val audioInputStream = AudioSystem.getAudioInputStream(file)
            val clip: Clip = AudioSystem.getClip()

            clip.addLineListener { event ->
                if (event.type == LineEvent.Type.STOP) {
                    clip.stop()
                    onSoundFinished()
                }
            }

            clip.open(audioInputStream)
            clip.start()
        } else {
            onSoundFinished()
        }
    }
}
