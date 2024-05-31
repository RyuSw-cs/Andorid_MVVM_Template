package com.ryusw.data.di

import com.ryusw.data.BuildConfig
import com.ryusw.data.local.datasource.AuthDataStore
import com.ryusw.data.remote.api.AuthApi
import com.ryusw.data.remote.interceptor.NetworkInterceptor
import com.ryusw.data.remote.interceptor.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal object NetworkModule {

    private const val SERVER_BASE_URL = "https://api.themoviedb.org/3/"

    @Singleton
    @Provides
    fun provideRetrofit(
        @Named("okHttpClient") okHttpClient: OkHttpClient
    ) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(SERVER_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @Named("okHttpClient")
    fun provideOkHttpInterceptor(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        @Named("networkInterceptor") networkInterceptor: NetworkInterceptor,
        @Named("tokenInterceptor") tokenInterceptor: TokenInterceptor
    ) : OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addNetworkInterceptor(tokenInterceptor)
            .addInterceptor(networkInterceptor)

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
    ) : NetworkInterceptor {
        return NetworkInterceptor(authDataStore,)
    }

    @Singleton
    @Provides
    @Named("tokenInterceptor")
    fun provideTokenInterceptor(
        authDataStore: AuthDataStore
    ) : TokenInterceptor {
        return TokenInterceptor(authDataStore)
    }
}