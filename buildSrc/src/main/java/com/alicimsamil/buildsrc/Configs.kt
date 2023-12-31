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

    // :core:database
    const val databaseNamespace = "com.alicimsamil.cointracker.core.database"
    const val databaseTestInstrumentationRunner = "com.alicimsamil.cointracker.core.database.runner.CoinTrackerTestRunner"

    // :core:firebase
    const val firebaseNamespace = "com.alicimsamil.cointracker.core.firebase"

    //:feature:listing
    const val listingNamespace = "com.alicimsamil.cointracker.feature.listing"

    //:feature:search
    const val searchNamespace = "com.alicimsamil.cointracker.feature.search"

    //:feature:detail
    const val detailNamespace = "com.alicimsamil.cointracker.feature.detail"

    //:feature:auth
    const val authNamespace = "com.alicimsamil.cointracker.feature.auth"

    //:feature:favorites
    const val favoritesNamespace = "com.alicimsamil.cointracker.feature.favorites"

}