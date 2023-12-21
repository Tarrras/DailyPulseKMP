plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("co.touchlab.skie") version "0.6.0"
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
        }

        androidMain.dependencies {
            implementation(libs.androidx.lifecycle.viewmodel.ktx)
        }

        iosMain.dependencies {

        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}


android {
    namespace = "com.foxdev.dailypulse"
    compileSdk = 34
    defaultConfig {
        minSdk = 26
    }
}
