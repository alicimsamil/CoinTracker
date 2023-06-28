plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = Configs.namespace
    compileSdk = Configs.compileSdkVersion

    defaultConfig {
        applicationId = Configs.applicationId
        minSdk = Configs.minSdkVersion
        targetSdk = Configs.targetSdkVersion
        versionCode = Configs.versionCode
        versionName = Configs.versionName
        testInstrumentationRunner = Configs.testInstrumentationRunner
    }

    flavorDimensions.add("version")

    productFlavors {
        create("qa"){
            dimension = "version"
            manifestPlaceholders["appName"] = Configs.testAppName
            applicationId = Configs.qaApplicationId
            buildConfigField(
                "String",
                "DATABASE_NAME",
                "\"COIN_TRACKER_DB_QA\""
            )
            buildConfigField(
                "int",
                "DATABASE_VERSION_CODE",
                "1"
            )
        }

        create("prod"){
            dimension = "version"
            manifestPlaceholders["appName"] = Configs.prodAppName
            applicationId = Configs.applicationId
            buildConfigField(
                "String",
                "DATABASE_NAME",
                "\"COIN_TRACKER_DB\""
            )
            buildConfigField(
                "int",
                "DATABASE_VERSION_CODE",
                "1"
            )
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isDebuggable = true
        }
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = Configs.javaVersion
        targetCompatibility = Configs.javaVersion
    }

    kotlinOptions {
        jvmTarget = Configs.jvmTarget
    }
}

dependencies {
    implementation(Dependencies.appModuleLibraries)
    testImplementation(Dependencies.testLibraries)
    kapt(Dependencies.hiltCompilerKaptLib)
}

kapt {
    correctErrorTypes = true
}