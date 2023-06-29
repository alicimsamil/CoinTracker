plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = Configs.databaseNamespace
    compileSdk = Configs.compileSdkVersion

    defaultConfig {
        minSdk = Configs.minSdkVersion
        targetSdk = Configs.targetSdkVersion

        testInstrumentationRunner = Configs.databaseTestInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
    }

    flavorDimensions.add("version")

    productFlavors {
        create("qa"){
            dimension = "version"
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
    implementation(Dependencies.databaseModuleLibraries)
    testImplementation(Dependencies.testLibraries)
    androidTestImplementation(Dependencies.androidTestLibraries)
    kapt(Dependencies.kaptLibraries)
    kaptTest(Dependencies.hiltCompilerKaptLib)
    kaptAndroidTest(Dependencies.hiltCompilerKaptLib)
    implementation(project(":core:common"))
    annotationProcessor(Dependencies.roomCompilerLib)
}

kapt {
    correctErrorTypes = true
}