plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = Configs.networkNamespace
    compileSdk = Configs.compileSdkVersion

    defaultConfig {
        minSdk = Configs.minSdkVersion
        targetSdk = Configs.targetSdkVersion
        testInstrumentationRunner = Configs.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
    }

    flavorDimensions.add("version")

    productFlavors {
        create("qa"){
            dimension = "version"
            buildConfigField(
                "String",
                "SERVICE_URL",
                "\"https://api.coingecko.com/api/v3/\""
            )
        }

        create("prod"){
            dimension = "version"
            buildConfigField(
                "String",
                "SERVICE_URL",
                "\"https://api.coingecko.com/api/v3/\""
            )
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = Configs.javaVersion
        targetCompatibility = Configs.javaVersion
    }

    buildFeatures {
        buildConfig = true
    }

    kotlinOptions {
        jvmTarget = Configs.jvmTarget
    }
}

dependencies {
    debugImplementation(Dependencies.chuckerLib)
    releaseImplementation(Dependencies.chuckerReleaseLib)
    implementation(Dependencies.networkModuleLibraries)
    kapt(Dependencies.hiltCompilerKaptLib)
    implementation(project(":core:common"))
    testImplementation(Dependencies.testLibraries)
}