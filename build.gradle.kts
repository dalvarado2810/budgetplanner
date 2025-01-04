buildscript {
    repositories {
        mavenCentral()
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.detekt.gradle.plugin)
    }
}

plugins {
    id("androidx.room") version "2.6.1" apply false
    id("org.jetbrains.compose") version "1.7.3" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0" apply false
    id("com.google.devtools.ksp") version "2.0.0-1.0.22" apply false
    id("io.gitlab.arturbosch.detekt") version "1.18.1" apply false
    id("com.android.application") version "8.0.0" apply false
    id("com.android.library") version "8.0.0" apply false
    id("org.jetbrains.kotlin.android") version "2.0.0" apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
}