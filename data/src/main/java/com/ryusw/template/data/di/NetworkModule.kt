package com.ryusw.template.data.di

import com.ryusw.template.data.local.datasource.AuthDataStore
import com.ryusw.template.data.remote.api.UserApi
import com.ryusw.template.data.remote.interceptor.NetworkInterceptor
import com.ryusw.template.data.remote.interceptor.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal object NetworkModule {

    private const val SERVER_BASE_URL = "https://example.com"

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(SERVER_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpInterceptor(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        @Named("networkInterceptor") networkInterceptor: Interceptor,
        @Named("tokenInterceptor") tokenInterceptor: Interceptor
    ) : OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(tokenInterceptor)
            .addNetworkInterceptor(networkInterceptor)

        if(BuildConfig.DEBUG){
            builder.addInterceptor(httpLoggingInterceptor)
        }

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    @Named("networkInterceptor")
    fun provideNetworkInterceptor(
        authDataStore: AuthDataStore,
        userApi : UserApi
    ) : Interceptor {
        return NetworkInterceptor(authDataStore, userApi)
    }

    @Singleton
    @Provides
    @Named("tokenInterceptor")
    fun provideTokenInterceptor(
        authDataStore: AuthDataStore
    ) : Interceptor {
        return TokenInterceptor(authDataStore)
    }
}