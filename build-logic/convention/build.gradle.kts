plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.pluginGradle)
    compileOnly(libs.kotlin.pluginGradle)
}

gradlePlugin {
    plugins {
        register("AndroidHilt") {
            id = "ryusw.hilt"
            implementationClass = "com.ryusw.convention.AndroidHiltConventionPlugin"
        }
        register("AndroidApplication") {
            id = "ryusw.application"
            implementationClass = "com.ryusw.convention.AndroidApplicationConventionPlugin"
        }
        register("AndroidCompose") {
            id = "ryusw.compose"
            implementationClass = "com.ryusw.convention.AndroidComposeConventionPlugin"
        }
        register("AndroidData") {
            id = "ryusw.data"
            implementationClass = "com.ryusw.convention.AndroidDataConventionPlugin"
        }
        register("AndroidFeature") {
            id = "ryusw.feature"
            implementationClass = "com.ryusw.convention.AndroidFeatureConventionPlugin"
        }
        register("AndroidLibrary") {
            id = "ryusw.android.library"
            implementationClass = "com.ryusw.convention.AndroidLibraryConventionPlugin"
        }
        register("JavaLibrary") {
            id = "ryusw.java.library"
            implementationClass = "com.ryusw.convention.JavaLibraryConventionPlugin"
        }
    }
}