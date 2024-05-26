package com.ryusw.template.data.di

import com.ryusw.template.data.remote.api.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
internal object ApiModule {
    @Provides
    fun provideUserApi(
        retrofit : Retrofit
    ) = retrofit.create(UserApi::class.java)
}