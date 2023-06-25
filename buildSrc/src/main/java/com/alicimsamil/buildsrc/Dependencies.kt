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

    //Navigation
    private const val navigationFragmentLib = "androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}"
    private const val navigationUiLib = "androidx.navigation:navigation-ui-ktx:${Versions.navVersion}"

    //Hilt
    private const val hiltLib = "com.google.dagger:hilt-android:${Versions.hiltLibraryVersion}"
    const val hiltCompilerKaptLib = "com.google.dagger:hilt-android-compiler:${Versions.hiltLibraryVersion}"

    //Coroutines
    private const val coroutinesLib = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"
    private const val coroutinesTestLib = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesVersion}"

    //Room
    private const val roomLib= "androidx.room:room-runtime:${Versions.roomVersion}"
    private const val roomKtxLib= "androidx.room:room-ktx:${Versions.roomVersion}"
    private const val roomCompilerLib= "androidx.room:room-compiler:${Versions.roomVersion}"
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
    private const val chuckerLib = "com.github.chuckerteam.chucker:library:${Versions.chuckerVersion}"
    private const val chuckerReleaseLib = "com.github.chuckerteam.chucker:library-no-op:${Versions.chuckerVersion}"

    //Splash Screen
    private const val splashScreenLib = "androidx.core:core-splashscreen:${Versions.splashScreenVersion}"

    //Glide
    private const val glideLib ="com.github.bumptech.glide:glide:${Versions.glideVersion}"

    //test
    private const val testJUnitLib = "junit:junit:${Versions.testJunitVersion}"
    private const val testExtJUnitLib = "androidx.test.ext:junit:${Versions.testExtJunitVersion}"
    private const val testEspressoCoreLib = "androidx.test.espresso:espresso-core:${Versions.testEspressoVersion}"

    val testLibraries = arrayListOf<String>().apply {
        add(testJUnitLib)
        add(coroutinesTestLib)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(testExtJUnitLib)
        add(testEspressoCoreLib)
    }

    val debugLibraries = arrayListOf<String>().apply {
        add(chuckerLib)
    }

    val releaseLibraries = arrayListOf<String>().apply {
        add(chuckerReleaseLib)
    }

    val kaptLibraries = arrayListOf<String>().apply {
        add(hiltCompilerKaptLib)
        add(roomCompilerLib)
    }

    val annotationLibraries = arrayListOf<String>().apply {
        add(roomCompilerLib)
    }

    val appModuleLibraries = arrayListOf<String>().apply {
        add(coreKtxLib)
        add(appCompatLib)
        add(constraintLayoutLib)
        add(materialLib)
        add(viewModelLib)
        add(activityLib)
        add(navigationUiLib)
        add(navigationFragmentLib)
        add(coroutinesLib)
        add(hiltLib)
        add(splashScreenLib)
    }
}