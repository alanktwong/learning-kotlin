import org.gradle.kotlin.dsl.`kotlin-dsl`

repositories {
    gradlePluginPortal()
    mavenCentral()
    jcenter()
}

plugins {
    `kotlin-dsl`
}

dependencies {
    // implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61")
    implementation(gradleApi())
    implementation(localGroovy())
}