package com.tanmar.data.di

import com.tanmar.data.github.GithubRepository
import com.tanmar.data.github.GithubRepositoryImpl
import com.tanmar.data.github.GithubService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideGitubRepository(retrofit: Retrofit): GithubRepository {
        val service = retrofit.create(GithubService::class.java)
        return GithubRepositoryImpl(service)
    }
}