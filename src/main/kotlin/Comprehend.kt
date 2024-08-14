package com.leantechniques.slack

import aws.sdk.kotlin.services.comprehend.ComprehendClient
import aws.sdk.kotlin.services.comprehend.model.DetectSentimentRequest
import aws.sdk.kotlin.services.comprehend.model.LanguageCode
import aws.sdk.kotlin.services.comprehend.model.SentimentScore

class Comprehend {

    suspend fun detectSentiments(fileName: String): SentimentScore? {
        val inputStream = this::class.java.classLoader.getResourceAsStream(fileName)
        val textVal = inputStream.bufferedReader().use { it.readText() }
        val request =
            DetectSentimentRequest {
                text = textVal
                languageCode = LanguageCode.fromValue("en")
            }

       return  ComprehendClient { region = "us-east-1" }.use { comClient ->
            val resp = comClient.detectSentiment(request)
            return resp.sentimentScore
        }
    }
}