package org.awong

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

object Config {
    object Versions {
        val kotlin= "1.3.61"
        val datagen="2.5.1"
        val spek="2.0.9"
        val kotest="3.4.2"
        val assertj="3.15.0"
        val mockito="3.3.3"
        val junitJupiter="5.2.0"

        val slf4jVersion="1.7.21"
        val log4jVersion="1.2.16"
        val logbackVersion="1.1.2"
    }

    object Libs {
        val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        val kotlinStdlibJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"

        val kotlinJunit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"

        val qalaDatagen = "io.qala.datagen:qala-datagen:${Versions.datagen}"
        val qalaDatagenJava8 = "io.qala.datagen:qala-datagen-java8types:${Versions.datagen}"

        val spek2 = "org.spekframework.spek2:spek-dsl-jvm:${Versions.spek}"
        val spek2Runner = "org.spekframework.spek2:spek-runner-junit5:${Versions.spek}"
        val kotest = "io.kotest:kotest-runner-junit5:${Versions.kotest}"

        val assertJ = "org.assertj:assertj-core:${Versions.assertj}"
        val mockito = "org.mockito:mockito-core:${Versions.mockito}"

        val junitJupiterApi = "org.junit.jupiter:junit-jupiter-api:${Versions.junitJupiter}"
        val junitJupiterParams = "org.junit.jupiter:junit-jupiter-params:${Versions.junitJupiter}"
        val junitJupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junitJupiter}"
    }
}

fun Project.kotlinProject() {
    dependencies {
        "compile"(kotlin("stdlib"))
        "implementation"(Config.Libs.kotlinStdlibJdk8)
        "implementation"(Config.Libs.kotlinReflect)
        "testCompile"(Config.Libs.kotlinJunit)

        "testRuntimeOnly"(Config.Libs.kotlinReflect)
    }
}

fun Project.junitProject() {
    dependencies {
        "testImplementation"(Config.Libs.assertJ)
        "testImplementation"(Config.Libs.mockito)

        "testImplementation"(Config.Libs.qalaDatagen)
        "testImplementation"(Config.Libs.qalaDatagenJava8)

        "testCompile"(Config.Libs.junitJupiterApi)
        "testCompile"(Config.Libs.junitJupiterParams)
        "testRuntime"(Config.Libs.junitJupiterEngine)
    }
}

fun Project.spekProject() {
    dependencies {
        // spek requires kotlin-reflect, can be omitted if already in the classpath
        "testImplementation"(Config.Libs.spek2)
        "testRuntimeOnly"(Config.Libs.spek2Runner)
    }
}
fun Project.kotestProject() {
    dependencies {
        "testImplementation"(Config.Libs.kotest)
    }
}