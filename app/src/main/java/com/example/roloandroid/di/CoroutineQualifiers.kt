package com.example.roloandroid.di

import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Qualifier


@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher
