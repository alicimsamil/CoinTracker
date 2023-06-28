import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * This function adds implementation dependencies sequentially.
 */
fun DependencyHandler.implementation(list: List<String>?) {
    list?.forEach { dependency ->
        add("implementation", dependency)
    }
}

/**
 * This function adds androidTestImplementation dependencies sequentially.
 */
fun DependencyHandler.androidTestImplementation(list: List<String>?) {
    list?.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

/**
 * This function adds testImplementation dependencies sequentially.
 */
fun DependencyHandler.testImplementation(list: List<String>?) {
    list?.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

/**
 * This function adds debugImplementation dependencies sequentially.
 */
fun DependencyHandler.debugImplementation(list: List<String>?) {
    list?.forEach { dependency ->
        add("debugImplementation", dependency)
    }
}

/**
 * This function adds releaseImplementation dependencies sequentially.
 */
fun DependencyHandler.releaseImplementation(list: List<String>?) {
    list?.forEach { dependency ->
        add("releaseImplementation", dependency)
    }
}

/**
 * This function adds annotationProcessor dependencies sequentially.
 */
fun DependencyHandler.annotationProcessor(list: List<String>) {
    list.forEach { dependency ->
        add("annotationProcessor", dependency)
    }
}

/**
 * This function adds kapt dependencies sequentially.
 */
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

/**
 * This function sequentially adds the dependencies we want to appear in other modules..
 */
fun DependencyHandler.api(list: List<String>?) {
    list?.forEach { dependency ->
        add("api", dependency)
    }
}