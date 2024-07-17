plugins {
    kotlin("jvm") version "1.9.23"
}

group = "com.leantechniques.slack"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("aws.sdk.kotlin:sts:1.2.51")
    implementation("aws.sdk.kotlin:rekognition:1.2.51")
    implementation("aws.sdk.kotlin:comprehend:1.2.51")
    implementation("aws.sdk.kotlin:transcribe:1.2.51")
    implementation("aws.sdk.kotlin:s3:1.0.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}