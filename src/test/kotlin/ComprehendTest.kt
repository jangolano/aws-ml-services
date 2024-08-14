import com.leantechniques.slack.Comprehend
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class ComprehendTest {

    @Test
    fun `Neutral Comprehend Test`() {
        runBlocking {
            val comprehend = Comprehend()
            val sentimentScore = comprehend.detectSentiments("us-constitution.txt")
            println("Sentiment Score Neutral: ${sentimentScore?.neutral?.times(100)}")
            println("Sentiment Score Negative: ${sentimentScore?.negative?.times(100)}")
            println("Sentiment Score Positive: ${sentimentScore?.positive?.times(100)}")
        }
    }


    @Test
    fun `Negative Comprehend Test`() {
        runBlocking {
            val comprehend = Comprehend()
            val sentimentScore = comprehend.detectSentiments("angry-customer.txt")
            println("Sentiment Score Neutral: ${sentimentScore?.neutral?.times(100)}")
            println("Sentiment Score Negative: ${sentimentScore?.negative?.times(100)}")
            println("Sentiment Score Positive: ${sentimentScore?.positive?.times(100)}")
        }
    }


    @Test
    fun `Positive Comprehend Test`() {
        runBlocking {
            val comprehend = Comprehend()
            val sentimentScore = comprehend.detectSentiments("positive-customer-service.txt")
            println("Sentiment Score Neutral: ${sentimentScore?.neutral?.times(100)}")
            println("Sentiment Score Negative: ${sentimentScore?.negative?.times(100)}")
            println("Sentiment Score Positive: ${sentimentScore?.positive?.times(100)}")
        }
    }
}

