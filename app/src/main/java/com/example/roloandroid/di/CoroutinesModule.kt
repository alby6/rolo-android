package com.example.roloandroid.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


@InstallIn(ApplicationComponent::class)
@Module
object CoroutinesModule {

    @IoDispatcher
    @Provides
    fun provideIoDispatcher() : CoroutineDispatcher = Dispatchers.IO

}
