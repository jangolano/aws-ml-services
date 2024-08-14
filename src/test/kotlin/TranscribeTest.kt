import com.leantechniques.slack.Comprehend
import com.leantechniques.slack.Transcribe
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class TranscribeTest {

    @Test
    fun `Transcribe Test`() {
        runBlocking {
           val transcribe = Transcribe()
            transcribe.transcribeAudio()
        }
    }
}

