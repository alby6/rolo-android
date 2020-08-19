package com.example.roloandroid.repo

import com.example.roloandroid.data.AppDatabase
import com.example.roloandroid.data.User
import com.example.roloandroid.googler_wrappers.Result
import com.example.roloandroid.repo.remote.RemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


@ExperimentalCoroutinesApi
@Singleton
class UserRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val appDatabase : AppDatabase
) {

    //In-memory cache of user. If this is available, opt to use this instead of drawing from database for better performance.
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

    fun getSaveToDisk(users : List<User>) {
        //appDatabase.userDao().insertAll(users.toTypedArray())
    }


    fun getUserCache() : Flow<Result<List<User>>> {
        return flow {
            if (cached == null) {
                emit(Result.Loading)
                emit(
                    Result.Success(appDatabase.userDao().getAll())
                )
            } else {
                cached?.let {
                    emit(Result.Success(it))
                }
            }
        }
    }




}