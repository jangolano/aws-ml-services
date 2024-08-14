package com.leantechniques.slack

import aws.sdk.kotlin.services.transcribe.TranscribeClient
import aws.sdk.kotlin.services.transcribe.model.*

class Transcribe {


    suspend fun transcribeAudio() {

        val request =
            StartTranscriptionJobRequest {
                languageCode =  LanguageCode.EnUs
                transcriptionJobName = "mlk-i-have-a-dream"
                media = Media{ mediaFileUri="s3://devup-20204-audio-input/MLKDream.mp3"}
                outputBucketName = "devup-2024-text-output"
                mediaSampleRateHertz = 22050
                mediaFormat = MediaFormat.Mp3
            }



        TranscribeClient{ region = "us-east-1" }.use { transcribe ->
            val response = transcribe.startTranscriptionJob(request)

        }
    }
}