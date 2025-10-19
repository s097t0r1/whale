import me.s097t0r1.build.versionFromCatalog

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "me.s097t0r1.whale"
    compileSdk {
        version = release(versionFromCatalog("compileSdk").toInt())
    }

    defaultConfig {
        applicationId = "me.s097t0r1.whale"
        minSdk = versionFromCatalog("minSdk").toInt()
        targetSdk = versionFromCatalog("targetSdk").toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
}

