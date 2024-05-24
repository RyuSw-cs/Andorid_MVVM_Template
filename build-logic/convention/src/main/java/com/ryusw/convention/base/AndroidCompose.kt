package com.ryusw.convention.base

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * 모듈에 사용할 Compose 정보를 제공하는 메소드
 * @param commonExtension 사용되는 모듈에 대한 정보
 * */
internal fun Project.configureAndroidCompose(
    commonExtension : CommonExtension<*,*,*,*,*>
) {
    // VersionCatalog에 대한 정보를 가져옴(implementation 하기 위함)
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    commonExtension.apply {
        with(buildFeatures){
            // compose 사용 설정
            compose = true
        }

        // compose compiler version
        composeOptions {
            // findVersion을 통해 [versions]에 있는 값을 가져올 수 있음
            kotlinCompilerExtensionVersion = libs.findVersion("androidxComposeCompiler").get().requiredVersion
        }
    }
    // 라이브러리 의존성 설정
    dependencies {
        // findLibrary를 통해 [libraries]에 있는 값을 가져올 수 있음
        val bom = libs.findLibrary("androidx-compose-bom").get()

        add("implementation", platform(bom))
        add("androidTestImplementation", platform(bom))

        add("implementation", libs.findLibrary("androidx-compose-foundation").get())
        add("implementation", libs.findLibrary("androidx-compose-foundation-layout").get())
        add("implementation", libs.findLibrary("androidx-compose-material").get())
        add("implementation", libs.findLibrary("androidx-compose-material-icons-extended").get())
        add("implementation", libs.findLibrary("androidx-compose-runtime").get())
        add("implementation", libs.findLibrary("androidx-compose-runtime-livedata").get())
        add("implementation", libs.findLibrary("androidx-compose-animation").get())
        add("implementation", libs.findLibrary("androidx-compose-ui-ui").get())
        add("implementation", libs.findLibrary("androidx-compose-ui-test").get())
        add("implementation", libs.findLibrary("androidx-compose-ui-test-manifest").get())
        add("implementation", libs.findLibrary("androidx-compose-ui-tooling").get())
        add("implementation", libs.findLibrary("androidx-compose-ui-tooling-preview").get())
        add("implementation", libs.findLibrary("androidx-compose-ui-util").get())
        add("implementation", libs.findLibrary("androidx-activity-compose").get())
        add("implementation", libs.findLibrary("androidx-navigation-compose").get())

        // Glide
        add("implementation", libs.findLibrary("glide-glide").get())
        add("implementation", libs.findLibrary("glide-compiler").get())
    }
}