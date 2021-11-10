package com.ss.universitiesdirectory.di

import android.content.Context
import com.ss.universitiesdirectory.repository.common.ApiService
import com.ss.universitiesdirectory.repository.news.NewsRepository
import com.ss.universitiesdirectory.repository.universities.UniversitiesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUniversities(apiService: ApiService) = UniversitiesRepository(apiService)

    @Provides
    @Singleton
    fun provideNewsRepository(@ApplicationContext context: Context) = NewsRepository(context)
}