package com.ss.universitiesdirectory.di

import android.content.Context
import com.ss.universitiesdirectory.data.remote.ApiService
import com.ss.universitiesdirectory.repository.news.NewsRepository
import com.ss.universitiesdirectory.manager.settings.SettingsManager
import com.ss.universitiesdirectory.repository.universities.UniversitiesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUniversities(
        apiService: ApiService
    ): UniversitiesRepository = UniversitiesRepository(apiService)

    @Provides
    @Singleton
    fun provideNewsRepository(
        @ApplicationContext context: Context
    ): NewsRepository = NewsRepository(context)

    @Provides
    @Singleton
    fun provideSettingsManager(
        @ApplicationContext context: Context
    ): SettingsManager = SettingsManager(context)
}