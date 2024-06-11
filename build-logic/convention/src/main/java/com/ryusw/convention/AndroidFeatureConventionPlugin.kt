package com.ryusw.convention

import com.android.build.gradle.LibraryExtension
import com.ryusw.convention.base.configureAndroidCompose
import com.ryusw.convention.base.configureCommonAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import java.util.Properties

/**
 * Feature 모듈에 적용하기 위함
 * */
class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            pluginManager.apply {
                apply("ryusw.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("androidx.navigation.safeargs.kotlin")
                apply("kotlin-parcelize")
                apply("ryusw.hilt")
            }

            extensions.configure<LibraryExtension> {
                configureCommonAndroid(this)
                configureAndroidCompose(this)
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("implementation", project(":domain"))
                add("implementation", project(":common-ui"))

                // Android Common
                add("implementation", libs.findLibrary("androidx-core").get())
                add("implementation", libs.findLibrary("androidx-appcompat").get())
                add("implementation", libs.findLibrary("android-material").get())

                // Android Ui 관련
                add("implementation", libs.findLibrary("androidx-constraintlayout").get())
                add("implementation", libs.findLibrary("facebook-shimmer").get())

                // Network
                add("implementation", libs.findLibrary("gson-gson").get())
                add("implementation", libs.findLibrary("squareup-gson-converter").get())
                add("implementation", libs.findLibrary("squareup-retrofit2").get())
                add("implementation", libs.findLibrary("squareup-okhttp").get())
                add("implementation", libs.findLibrary("squareup-okhttp-interceptor").get())

                // Glide
                add("implementation", libs.findLibrary("glide-glide").get())
                add("implementation", libs.findLibrary("glide-compiler").get())

                // Navigation
                add("implementation", libs.findBundle("androidx-navigation").get())
                add("implementation", libs.findLibrary("androidx-navigation-test").get())

                // Coroutine Scope
                add("implementation", libs.findBundle("lifecycle").get())
                add("implementation", libs.findBundle("kotlinx-coroutine").get())

                // Data Store
                add("implementation", libs.findBundle("datastore").get())

                // paging
                add("implementation", libs.findLibrary("androidx-paging").get())
            }
        }
    }
}