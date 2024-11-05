package com.leantechniques.slack

import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.s3.model.GetObjectRequest
import aws.sdk.kotlin.services.transcribe.TranscribeClient
import aws.sdk.kotlin.services.transcribe.model.*
import aws.smithy.kotlin.runtime.content.writeToFile
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.io.File
import java.util.*

class Transcribe {

    private val jobName = UUID.randomUUID().toString()
    private val doneStatuses = arrayOf("COMPLETED", "FAILED")
    suspend fun transcribeAudio() {
        val request = StartTranscriptionJobRequest {
            languageCode = LanguageCode.EnUs
            transcriptionJobName = jobName
            media = Media { mediaFileUri = "s3://cloudx-2024-audio-input/MLKDream.mp3" }
            outputBucketName = "cloudx-2024-output-text"
            mediaSampleRateHertz = 22050
            mediaFormat = MediaFormat.Mp3
        }

        TranscribeClient { region = "us-east-1" }.use { transcribe ->
            transcribe.startTranscriptionJob(request)
            var jobStatus = getJobStatus(transcribe)
            while (!doneStatuses.contains(jobStatus)) {
                delay(1000)
                jobStatus = getJobStatus(transcribe)
            }
            if (jobStatus === "COMPLETED") {
                runBlocking {
                    downloadFile("/Users/jangolano/repos/conference-talks/kotlin-cloud/aws-ml-services/src/test/resources/${jobName}.json")
                    println("File Downloaded: $jobName")
                }
            }
        }

    }


    //Check to see if the job is completed
    private fun getJobStatus(transcribeClient: TranscribeClient): String? {
        lateinit var job: GetTranscriptionJobResponse
        runBlocking {
            job = transcribeClient.getTranscriptionJob(GetTranscriptionJobRequest { transcriptionJobName = jobName })
        }
        val status = job.transcriptionJob?.transcriptionJobStatus?.value
        println(status)
        return status
    }

    //Download the file with the results
    private suspend fun downloadFile(
        path: String,
    ) {
        val request = GetObjectRequest {
            key = "${jobName}.json"
            bucket = "cloudx-2024-output-text"
        }

        S3Client { region = "us-east-1" }.use { s3 ->
            s3.getObject(request) { resp ->
                val myFile = File(path)
                resp.body?.writeToFile(myFile)
            }
        }
    }

}