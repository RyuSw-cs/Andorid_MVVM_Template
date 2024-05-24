pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "ryusw"
include(":app")
include(":common")
include(":feature")
include(":domain")
include(":data")
include(":feature:movie-search")
include(":feature:movie-detail")
