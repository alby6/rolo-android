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


class RemoteDataSource @Inject constructor(
    val remoteDataDownloader: RemoteDataDownloader
) {
    fun executeRequest() : RemoteData? {
        val response = try {
            remoteDataDownloader.executeRequest()
        } catch (e : Exception) {
            throw e
        }

        //check if response body is valid
        return UserDataParser.parseData(response.body!!)
    }

}