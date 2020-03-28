import org.gradle.kotlin.dsl.`kotlin-dsl`

repositories {
    gradlePluginPortal()
    mavenCentral()
    jcenter()
}

plugins {
    `kotlin-dsl`
    java
}

gradlePlugin {
    plugins {
        register("greet") {
            id = "greeter"
            implementationClass = "org.awong.plugins.GreetPlugin"
        }
    }
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

tasks {
    test {
        useJUnitPlatform()
        testLogging {
            events("FAILED", "SKIPPED")
        }
    }
}

dependencies {
    // implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61")
    implementation(gradleApi())
    implementation(localGroovy())

    val kotlinVersion = "1.3.61"
    testCompile("org.jetbrains.kotlin:kotlin-test-junit:${kotlinVersion}")
    testRuntimeOnly("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")

    val junitJupiter = "5.2.0"
    testCompile("org.junit.jupiter:junit-jupiter-api:${junitJupiter}")
    testCompile("org.junit.jupiter:junit-jupiter-params:${junitJupiter}")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:${junitJupiter}")
}