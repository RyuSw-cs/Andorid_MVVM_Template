package com.ryusw.data.di

import android.content.Context
import com.ryusw.data.local.datasource.AuthDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object LocalModule {
    @Provides
    @Singleton
    fun provideAuthDataStore(
        @ApplicationContext context: Context,
    ) = AuthDataStore(context)
}