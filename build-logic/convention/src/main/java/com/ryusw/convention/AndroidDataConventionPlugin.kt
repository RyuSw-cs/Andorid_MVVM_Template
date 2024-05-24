package com.ryusw.convention

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import java.util.*

/**
 * 데이터 모듈에 적용하기 위함
 * */
class AndroidDataConventionPlugin : Plugin<Project>{
    override fun apply(target: Project) {
        with(target){
            pluginManager.apply {
                // 'AndroidLibrary
                apply("ryusw.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("kotlin-parcelize")
                apply("ryusw.hilt")
            }

            val properties = Properties()
            properties.load(project.rootProject.file("local.properties").inputStream())

            extensions.configure<LibraryExtension> {
                compileSdk = 34
                defaultConfig {
                    minSdk = 24
                    buildFeatures {
                        buildConfig = true
                    }
                }
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {

                add("implementation", project(":domain"))

                // Network
                add("implementation", libs.findLibrary("gson-gson").get())
                add("implementation", libs.findLibrary("squareup-gson-converter").get())
                add("implementation", libs.findLibrary("squareup-retrofit2").get())
                add("implementation", libs.findLibrary("squareup-okhttp").get())
                add("implementation", libs.findLibrary("squareup-okhttp-interceptor").get())

                // Coroutine
                add("implementation", libs.findLibrary("kotlin-coroutine-core").get())

                // DataStore
                add("implementation", libs.findBundle("datastore").get())
            }
        }
    }
}