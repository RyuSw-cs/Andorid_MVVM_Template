package com.ryusw.template.data.di

import android.content.Context
import com.ryusw.template.data.local.datasource.AuthDataStore
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Singleton
@InstallIn(SingletonComponent::class)
internal object LocalModule {
    @Provides
    @Singleton
    fun provideAuthDataStore(
        @ApplicationContext context: Context,
    ) = AuthDataStore(context)
}