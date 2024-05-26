package com.ryusw.template.data.di

import com.ryusw.template.data.repository.AuthRepositoryImpl
import com.ryusw.template.data.repository.UserRepositoryImpl
import com.ryusw.template.domain.repository.AuthRepository
import com.ryusw.template.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl) : AuthRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl) : UserRepository
}