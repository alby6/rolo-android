package com.example.roloandroid.repo


import android.os.SystemClock
import com.example.roloandroid.data.User
import com.example.roloandroid.googler_wrappers.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


class RemoteDataDownloader @Inject constructor(
    val okHttpClient: OkHttpClient
) {

    companion object  {
        const val URL = "https://51ab3c19-6483-4cd8-81d2-ece629815aa2.mock.pstmn.io/users"
    }

    fun executeRequest() : Response {
        //blocking call
        val request = Request
            .Builder()
            .url(URL)
            .build()
        return  okHttpClient.newCall(request).execute()
    }

}
