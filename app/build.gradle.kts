plugins {
    id("com.android.application")
    id("kotlin-android")
    id("io.gitlab.arturbosch.detekt")

    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.daniel.budgetplanner"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.daniel.budgetplanner"
        minSdk = 28
        targetSdk = 35
        versionCode = 8
        versionName = "1.1.2"
        setProperty("archivesBaseName", "budgetPlanner-$versionName-($versionCode)" )

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug { }
        release {
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlinOptions {
        jvmTarget = "21"
        freeCompilerArgs += listOf("-Xcontext-receivers")
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeCompiler {
        reportsDestination = file("build/outputs/compose.reports")
        metricsDestination = file("build/outputs/compose.metrics")
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {

    //Base
    implementation(libs.bundles.base)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.material)

    //Products and Navigation
    implementation(libs.bundles.products)
    implementation(libs.bundles.navigations)

    //Navigation
    implementation(libs.navigation.compose)

    //Dependency Injection
    implementation(libs.bundles.koin)

    //UI
    implementation (libs.material)

    //Test
    testImplementation (libs.junit)
    androidTestImplementation (libs.androidx.junit)
    androidTestImplementation (libs.androidx.espresso.core)
    androidTestImplementation (libs.androidx.ui.test.junit4)
    debugImplementation (libs.androidx.ui.tooling)
    debugImplementation (libs.androidx.ui.test.manifest)
}