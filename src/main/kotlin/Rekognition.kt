package com.leantechniques.slack

import aws.sdk.kotlin.services.rekognition.RekognitionClient
import aws.sdk.kotlin.services.rekognition.model.DetectLabelsRequest
import aws.sdk.kotlin.services.rekognition.model.Image
import aws.sdk.kotlin.services.rekognition.model.Label

class Rekognition {


    suspend fun findLabels(fileName: String) :List<Label>?{
        val fullFilePath = this::class.java.classLoader.getResourceAsStream(fileName)
        val picture = Image {
            bytes = fullFilePath?.readAllBytes()
        }
        val request = DetectLabelsRequest {
                image = picture
            }
        RekognitionClient{ region = "us-east-1" }.use { rekognition ->
            val response = rekognition.detectLabels(request)
            return response.labels
        }
    }
}