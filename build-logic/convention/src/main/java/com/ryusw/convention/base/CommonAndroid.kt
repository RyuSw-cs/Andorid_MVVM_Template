package com.ryusw.convention.base

import com.android.build.api.dsl.BuildFeatures
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * Android 모듈에 사용되는 중복 라이브러리 구현
 * */
internal fun Project.configureCommonAndroid(
    commonExtension : CommonExtension<*,*,*,*,*>
) {
    commonExtension.apply {

        with(buildFeatures){
            dataBinding {
                enable = true
            }
        }

        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
        dependencies {
            add("implementation", libs.findLibrary("junit-junit").get())
            add("implementation", libs.findLibrary("androidx-test-junit").get())
            add("implementation", libs.findLibrary("androidx-test-espresso").get())
        }
    }
}