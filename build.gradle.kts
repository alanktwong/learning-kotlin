import org.awong.Config.Libs
import org.awong.junitProject
import org.awong.kotlinProject
import org.awong.spekProject

plugins {
    java
    `java-library`
    `maven-publish`
    idea
    kotlin("jvm") version "1.3.61"
}

java {
    sourceCompatibility=JavaVersion.VERSION_11
    targetCompatibility=JavaVersion.VERSION_11

    // assert(kotlin.collections.listOf("1.8" , "11", "12", "13") System.getProperty("java.specification.version"))
}

repositories {
    mavenCentral()
    jcenter()
}

group = "org.awong"
version = "0.0.1-SNAPSHOT"

tasks {
    test {
        useJUnitPlatform()
        testLogging {
            events("FAILED", "SKIPPED")
        }
    }
}

dependencies {
    junitProject()
    kotlinProject()
    spekProject()
}

tasks.wrapper {
    gradleVersion = "6.3"
}