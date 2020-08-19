package com.example.roloandroid.repo

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class UserRepository @Inject constructor(
    val remoteDataSource: RemoteDataSource,
    val localDataSource: LocalDataSource
) {

    fun executeRemoteDataRequest() {
        remoteDataSource.executeRequest()
    }

    fun getRemoteDataObservable() : Flow<Long> {
        return remoteDataSource.dataLastUpdatedObservable
    }




}