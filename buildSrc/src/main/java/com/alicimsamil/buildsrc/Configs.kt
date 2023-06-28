import org.gradle.api.JavaVersion

/**
 * This class contains build.gradle configurations.
 */
object Configs {
    const val compileSdkVersion  = 33
    const val minSdkVersion  = 24
    const val targetSdkVersion  = 33
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val namespace = "com.alicimsamil.cointracker"
    const val applicationId = "com.alicimsamil.cointracker"
    const val qaApplicationId = "com.alicimsamil.cointracker.qa"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val prodAppName = "Coin Tracker"
    const val testAppName = "Coin Tracker Test"
    const val jvmTarget = "17"
    val javaVersion = JavaVersion.VERSION_17

    // :core:common
    const val commonNamespace = "com.alicimsamil.cointracker.core.common"

    // :core:network
    const val networkNamespace = "com.alicimsamil.cointracker.core.network"

    // :core:testing
    const val testingNamespace = "com.alicimsamil.cointracker.core.testing"

}