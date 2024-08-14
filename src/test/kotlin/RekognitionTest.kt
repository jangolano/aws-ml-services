import com.leantechniques.slack.Rekognition
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test

class RekognitionTest {

    @Test
    fun testRekognition() {
        runBlocking {
            val rekognition = Rekognition()
            val labels = rekognition.findLabels("camping.jpeg")
            if(labels != null) {
                labels?.forEach {
                   println("Item Found: ${it.name}, Confidence: ${it.confidence}%")
                }
            }else{
                fail()
            }
        }
    }


}