package com.example.roloandroid.repo.remote


import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject


class RemoteDataDownloader @Inject constructor(
    private val okHttpClient: OkHttpClient
) {

    companion object  {
        const val BASE_URL = "https://51ab3c19-6483-4cd8-81d2-ece629815aa2.mock.pstmn.io/users"
    }

    fun executeRequest(url : String) : Response {
        //blocking call
        val request = Request
            .Builder()
            .url(url)
            .build()
        return  okHttpClient.newCall(request).execute()
    }

}
