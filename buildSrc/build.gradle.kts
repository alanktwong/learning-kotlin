import org.gradle.kotlin.dsl.`kotlin-dsl`
import kotlin.collections.mapOf

repositories {
    gradlePluginPortal()
    mavenCentral()
    jcenter()
}

plugins {
    `kotlin-dsl`
    java
    groovy
}

tasks {
    test {
        useJUnitPlatform()
        testLogging {
            events("FAILED", "SKIPPED")
        }
    }
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

val groovyVersion = "2.5.9"
val kotlinVersion = "1.3.61"
val junitJupiterVersion = "5.2.0"
val spockVersion = "1.3-groovy-2.5"

dependencies {
    // implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61")
    compile("org.codehaus.groovy:groovy:${groovyVersion}")
    implementation(gradleApi())

    testCompile("org.jetbrains.kotlin:kotlin-test-junit:${kotlinVersion}")
    testRuntimeOnly("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")

    testCompile("org.junit.jupiter:junit-jupiter-api:${junitJupiterVersion}")
    testCompile("org.junit.jupiter:junit-jupiter-params:${junitJupiterVersion}")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:${junitJupiterVersion}")

    testCompile("org.spockframework:spock-core:${spockVersion}") {
        exclude(mapOf("module" to "groovy-all"))
    }
}