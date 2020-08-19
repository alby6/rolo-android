package com.example.roloandroid.repo

import android.os.SystemClock
import com.example.roloandroid.data.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RemoteDataSource @Inject constructor(
    val okHttpClient: OkHttpClient
) {

    companion object  {
        const val URL = "URL"
    }

    var cached : List<User>?  = null

    @ExperimentalCoroutinesApi
    private val dataLastUpdatedChannel = BroadcastChannel<Long>(Channel.CONFLATED)

    @ExperimentalCoroutinesApi
    val dataLastUpdatedObservable = dataLastUpdatedChannel.asFlow()



    fun getUserCache() : List<User>? {
        return cached
    }

    fun executeRequest() {
        //blocking call
        val request = Request
            .Builder()
            .url(URL)
            .build()

        val result = okHttpClient.newCall(request).execute()


        dataLastUpdatedChannel.offer(SystemClock.currentThreadTimeMillis())

    }

}