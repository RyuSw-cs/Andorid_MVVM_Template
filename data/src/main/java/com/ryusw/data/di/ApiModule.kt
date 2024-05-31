package com.ryusw.data.di

import com.ryusw.data.remote.api.AuthApi
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
    ) : AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
}