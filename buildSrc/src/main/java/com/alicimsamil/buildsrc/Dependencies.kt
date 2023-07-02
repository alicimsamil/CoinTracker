/**
 * This class keeps dependencies.
 */
object Dependencies {

    //app
    private const val materialLib= "com.google.android.material:material:${Versions.materialLibVersion}"
    private const val coreKtxLib = "androidx.core:core-ktx:${Versions.coreLibVersion}"
    private const val appCompatLib = "androidx.appcompat:appcompat:${Versions.appcompatLibVersion}"
    private const val constraintLayoutLib = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    private const val viewModelLib = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelVersion}"
    private const val activityLib = "androidx.activity:activity-ktx:${Versions.activityVersion}"

    //Kotlin
    private const val serializationLib = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serializationVersion}"

    //Navigation
    private const val navigationFragmentLib = "androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}"
    private const val navigationUiLib = "androidx.navigation:navigation-ui-ktx:${Versions.navVersion}"

    //Hilt
    private const val hiltLib = "com.google.dagger:hilt-android:${Versions.hiltLibraryVersion}"
    const val hiltCompilerKaptLib = "com.google.dagger:hilt-android-compiler:${Versions.hiltLibraryVersion}"
    const val hiltTestingLib = "com.google.dagger:hilt-android-testing:${Versions.hiltLibraryVersion}"

    //Coroutines
    const val coroutinesLib = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"
    const val coroutinesTestLib = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesVersion}"

    //Room
    private const val roomLib= "androidx.room:room-runtime:${Versions.roomVersion}"
    private const val roomKtxLib= "androidx.room:room-ktx:${Versions.roomVersion}"
    const val roomCompilerLib= "androidx.room:room-compiler:${Versions.roomVersion}"
    private const val roomPagingLib = "androidx.room:room-paging:${Versions.roomVersion}"

    //Paging
    private const val pagingLib = "androidx.paging:paging-common-ktx:${Versions.pagingVersion}"
    private const val pagingRuntimeLib = "androidx.paging:paging-runtime-ktx:${Versions.pagingVersion}"

    //Retrofit
    private const val retrofitLib = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    private const val retrofitConverterLib = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    //OkHttp
    private const val okHttpLib = "com.squareup.okhttp3:okhttp:${Versions.okHttpVersion}"
    private const val okHttpInterceptorLib = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpVersion}"

    //Chucker
    const val chuckerLib = "com.github.chuckerteam.chucker:library:${Versions.chuckerVersion}"
    const val chuckerReleaseLib = "com.github.chuckerteam.chucker:library-no-op:${Versions.chuckerVersion}"

    //Splash Screen
    private const val splashScreenLib = "androidx.core:core-splashscreen:${Versions.splashScreenVersion}"

    //Glide
    private const val glideLib ="com.github.bumptech.glide:glide:${Versions.glideVersion}"
    private const val swipeToRefreshLib =  "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeToRefreshVersion}"

    //Firebase
    const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseVersion}"
    private const val firebaseAnalytcsLib = "com.google.firebase:firebase-analytics-ktx"
    private const val firebaseAuthLib = "com.google.firebase:firebase-auth-ktx"
    private const val firebaseFirestoreLib = "com.google.firebase:firebase-firestore-ktx"

    //test
    const val testJUnitLib = "junit:junit:${Versions.testJunitVersion}"
    private const val testExtJUnitLib = "androidx.test.ext:junit:${Versions.testExtJunitVersion}"
    private const val testEspressoCoreLib = "androidx.test.espresso:espresso-core:${Versions.testEspressoVersion}"

    val testLibraries = arrayListOf<String>().apply {
        add(testJUnitLib)
        add(coroutinesTestLib)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(testExtJUnitLib)
        add(testEspressoCoreLib)
        add(hiltTestingLib)
    }

    val kaptLibraries = arrayListOf<String>().apply {
        add(hiltCompilerKaptLib)
        add(roomCompilerLib)
    }

    val annotationLibraries = arrayListOf<String>().apply {
        add(roomCompilerLib)
    }

    val appModuleLibraries = arrayListOf<String>().apply {
        add(activityLib)
        add(coroutinesLib)
        add(hiltLib)
        add(splashScreenLib)
    }

    val networkModuleLibraries = arrayListOf<String>().apply {
        add(okHttpLib)
        add(okHttpInterceptorLib)
        add(retrofitLib)
        add(retrofitConverterLib)
        add(coroutinesLib)
        add(hiltLib)
        add(serializationLib)
    }

    val databaseModuleLibraries = arrayListOf<String>().apply {
        add(roomLib)
        add(roomKtxLib)
        add(roomPagingLib)
        add(coroutinesLib)
        add(hiltLib)
    }

    val commonModuleLibraries = arrayListOf<String>().apply {
        add(glideLib)
        add(swipeToRefreshLib)
    }

    val uiModuleLibraries = arrayListOf<String>().apply {
        add(coreKtxLib)
        add(appCompatLib)
        add(constraintLayoutLib)
        add(materialLib)
        add(viewModelLib)
        add(navigationUiLib)
        add(navigationFragmentLib)
    }

    val firebaseModuleLibraries = arrayListOf<String>().apply {
        add(coroutinesLib)
        add(hiltLib)
        add(firebaseAnalytcsLib)
        add(firebaseAuthLib)
        add(firebaseFirestoreLib)
    }

    val listingModuleLibraries = arrayListOf<String>().apply {
        add(coroutinesLib)
        add(hiltLib)
        add(pagingLib)
        add(pagingRuntimeLib)
        add(retrofitLib)
    }

}