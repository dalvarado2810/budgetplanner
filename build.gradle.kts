plugins {
    id("io.gitlab.arturbosch.detekt") version "1.18.1" apply false
}

buildscript {
    repositories {
        mavenCentral()
        google()
        mavenCentral()
        mavenLocal()
    }

    dependencies {
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.android.gradle.plugin)
        classpath(libs.detekt.gradle.plugin)
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
        mavenCentral()
        mavenLocal()
    }
}