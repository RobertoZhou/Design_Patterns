import com.github.spotbugs.snom.Confidence
import com.github.spotbugs.snom.Effort

plugins {
    java
    application
    id("com.github.spotbugs") version "6.5.10"
    id("com.diffplug.spotless") version "8.9.0"
}

group = "br.com.pucpr"
version = "1.0-SNAPSHOT"


application {
    mainClass = "br.pucpr.Main"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

spotbugs {
    ignoreFailures = false
    showStackTraces = true
    showProgress = true
    effort = Effort.DEFAULT
    reportLevel = Confidence.DEFAULT
    excludeFilter.set(file("$rootDir/config/spotbugs/exclude.xml"))
    reportsDir = file("${layout.buildDirectory}/spotbugs")
}

spotless {
    java {
        googleJavaFormat()
    }
}


