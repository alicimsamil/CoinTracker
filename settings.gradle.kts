pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "CoinTracker"
include(":app")
include(":core:common")
include(":core:network")
include(":core:database")
include(":core:firebase")
include(":core:ui")
include(":feature:listing")
include(":feature:search")
include(":feature:detail")
