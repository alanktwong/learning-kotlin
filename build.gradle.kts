import org.awong.Config.Libs

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
    compile(Libs.kotlinStdlib)
    implementation(Libs.kotlinStdlibJdk8)
    implementation(Libs.kotlinReflect)
    testCompile(Libs.kotlinJunit)

    testImplementation(Libs.qalaDatagen)
    testImplementation(Libs.qalaDatagenJava8)

    // spek requires kotlin-reflect, can be omitted if already in the classpath
    testImplementation(Libs.spek2)
    testRuntimeOnly(Libs.spek2Runner)

    testImplementation(Libs.assertJ)
    testImplementation(Libs.mockito)

    testCompile(Libs.junitJupiterApi)
    testCompile(Libs.junitJupiterParams)
    testRuntime(Libs.junitJupiterEngine)
    testRuntimeOnly(Libs.kotlinReflect)
}

tasks.wrapper {
    gradleVersion = "6.3"
}