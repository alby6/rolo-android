package com.example.roloandroid.repo

import com.example.roloandroid.data.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject



class UserRepository @Inject constructor(
    val remoteDataSource: RemoteDataSource,
    val localDataSource: LocalDataSource
) {

    //in memory cache of user. Opt to use this instead of database if available for better performance
    var cached : List<User>?  = null

    @ExperimentalCoroutinesApi
    private val dataLastUpdatedChannel = BroadcastChannel<Long>(Channel.CONFLATED)
    @ExperimentalCoroutinesApi
    val dataLastUpdatedObservable = dataLastUpdatedChannel.asFlow()


    fun getUserCache() : List<User>? {
        return cached
    }
    fun executeRemoteDataRequest() {
        remoteDataSource.executeRequest()
        dataLastUpdatedChannel.offer(System.currentTimeMillis())

    }

    fun getRemoteDataObservable() : Flow<Long> {
        return dataLastUpdatedObservable
    }




}