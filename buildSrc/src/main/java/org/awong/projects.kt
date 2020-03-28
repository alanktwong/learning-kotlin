package org.awong

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

import org.awong.Config.Libs

fun Project.kotlinProject() {
    dependencies {
        "compile"(kotlin("stdlib"))
        "implementation"(Libs.kotlinStdlibJdk8)
        "implementation"(Libs.kotlinReflect)
        "testCompile"(Libs.kotlinJunit)

        "testRuntimeOnly"(Libs.kotlinReflect)
    }
}

fun Project.junitProject() {
    dependencies {
        "testImplementation"(Libs.assertJ)
        "testImplementation"(Libs.mockito)

        "testImplementation"(Libs.qalaDatagen)
        "testImplementation"(Libs.qalaDatagenJava8)

        "testCompile"(Libs.junitJupiterApi)
        "testCompile"(Libs.junitJupiterParams)
        "testRuntime"(Libs.junitJupiterEngine)
    }
}

fun Project.spekProject() {
    dependencies {
        // spek requires kotlin-reflect, can be omitted if already in the classpath
        "testImplementation"(Libs.spek2)
        "testRuntimeOnly"(Libs.spek2Runner)
    }
}

fun Project.kotestProject() {
    dependencies {
        "testImplementation"(Libs.kotest)
    }
}