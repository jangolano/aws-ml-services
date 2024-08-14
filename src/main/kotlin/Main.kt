package com.leantechniques.slack

import aws.sdk.kotlin.services.transcribe.TranscribeClient
import aws.sdk.kotlin.services.transcribe.model.LanguageCode
import aws.sdk.kotlin.services.transcribe.model.Media
import aws.sdk.kotlin.services.transcribe.model.MediaFormat
import aws.sdk.kotlin.services.transcribe.model.StartTranscriptionJobRequest
import kotlinx.coroutines.runBlocking

fun main() {

    runBlocking {
        transcribeAudio(
            """
             COBOL is awful
        """.trimIndent()
        )
    }

}

suspend fun transcribeAudio(documentText: String) {

    val request =
        StartTranscriptionJobRequest {
            languageCode =  LanguageCode.EnUs
            transcriptionJobName = "josh-job-try2"
            media = Media{ mediaFileUri="s3://nebraska-code-input/tech-support.mp3"}
            outputBucketName = "nebraska-code-2024"
            mediaSampleRateHertz = 44100
            mediaFormat = MediaFormat.Mp3
        }


     TranscribeClient{ region = "us-east-2" }.use { transcribe ->
        val response = transcribe.startTranscriptionJob(request)
        println(response.transcriptionJob)

    }
}

