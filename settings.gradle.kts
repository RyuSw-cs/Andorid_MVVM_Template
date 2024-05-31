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
include(":common-ui")
include(":feature")
include(":domain")
include(":data")
include(":feature:login")
include(":feature:splash")
include(":feature:movie-list")
