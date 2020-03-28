package org.awong

object Config {
    object Versions {
        val kotlinVersion= "1.3.61"
        val datagenVersion="2.5.1"
        val spekVersion="2.0.9"
        val kotestVersion="3.4.2"
        val assertjVersion="3.15.0"
        val mockitoVersion="3.3.3"
        val junitJupiterVersion="5.2.0"

        val slf4jVersion="1.7.21"
        val log4jVersion="1.2.16"
        val logbackVersion="1.1.2"
    }

    object BuildPlugins {
        val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    }

    object Libs {
        val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
        val kotlinStdlibJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlinVersion}"
        val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlinVersion}"

        val kotlinJunit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlinVersion}"

        val qalaDatagen = "io.qala.datagen:qala-datagen:${Versions.datagenVersion}"
        val qalaDatagenJava8 = "io.qala.datagen:qala-datagen-java8types:${Versions.datagenVersion}"

        val spek2 = "org.spekframework.spek2:spek-dsl-jvm:${Versions.spekVersion}"
        val spek2Runner = "org.spekframework.spek2:spek-runner-junit5:${Versions.spekVersion}"
        // val kotest = "io.kotest:kotest-runner-junit5:${Versions.kotestVersion}"

        val assertJ = "org.assertj:assertj-core:${Versions.assertjVersion}"
        val mockito = "org.mockito:mockito-core:${Versions.mockitoVersion}"

        val junitJupiterApi = "org.junit.jupiter:junit-jupiter-api:${Versions.junitJupiterVersion}"
        val junitJupiterParams = "org.junit.jupiter:junit-jupiter-params:${Versions.junitJupiterVersion}"
        val junitJupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junitJupiterVersion}"
    }
}