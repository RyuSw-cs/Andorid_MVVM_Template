package com.ryusw.convention

import com.android.build.gradle.LibraryExtension
import com.ryusw.convention.base.configureAndroidCompose
import com.ryusw.convention.base.configureCommonAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

/**
 * 디자인 모듈에 적용
 * */
class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidCompose(extension)
            configureCommonAndroid(extension)
        }
    }
}