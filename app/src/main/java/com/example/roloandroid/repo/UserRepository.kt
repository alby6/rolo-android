package com.example.roloandroid.repo

import com.example.roloandroid.data.User
import com.example.roloandroid.googler_wrappers.Result
import com.example.roloandroid.repo.local.LocalDataSource
import com.example.roloandroid.repo.remote.RemoteDataSource
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserRepository @Inject constructor(
    val remoteDataSource: RemoteDataSource,
    val localDataSource: LocalDataSource
) {

    //in memory cache of user. Opt to use this instead of database if available for better performance
    var cached : List<User>?  = null

    private val dataLastUpdatedChannel = BroadcastChannel<Long>(Channel.CONFLATED)
    val dataLastUpdatedObservable : Flow<Long> = dataLastUpdatedChannel.asFlow()


    fun executeRemoteDataRequest() {
        val remoteData = remoteDataSource.executeRequest()
        remoteData?.let {
            //save to cache
            cached = remoteData.users
            //save to disk

        }

        //let view model know that new information is available
        dataLastUpdatedChannel.offer(System.currentTimeMillis())




    }

    fun getUserCache() : Flow<Result<List<User>>> {
        return flow {
            if (cached == null)
                emit(
                    Result.Error(
                        Exception("Empty Cache")
                    )
            ) else {
                emit(Result.Success(cached!!))
            }
        }
    }




}