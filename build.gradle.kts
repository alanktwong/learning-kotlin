import org.awong.groovyProject
import org.awong.junitProject
import org.awong.kotlinProject
import org.awong.spekProject
import kotlin.collections.listOf

plugins {
    java
    `java-library`
    `maven-publish`
    idea
    greeter
    groovy
    kotlin("jvm") version "1.3.61"
}

java {
    assert(listOf("1.8", "11", "12", "13").contains(System.getProperty("java.specification.version")))

    sourceCompatibility=JavaVersion.VERSION_11
    targetCompatibility=JavaVersion.VERSION_11
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
    wrapper {
        gradleVersion = "6.3"
    }
}

dependencies {
    junitProject()
    kotlinProject()
    spekProject()
    groovyProject()
}