package com.ryusw.template.data.di

import com.ryusw.template.data.remote.api.AuthApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
internal object ApiModule {
    @Provides
    fun provideAuthApi(
        retrofit : Retrofit
    ) = retrofit.create(AuthApi::class.java)
}