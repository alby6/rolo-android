package com.example.roloandroid.repo.remote

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject


class RemoteDataSource @Inject constructor(
    private val remoteDataDownloader: RemoteDataDownloader
) {
    suspend fun executeRequest() : RemoteData? {
        val response = try {
            remoteDataDownloader.executeRequest(
                RemoteDataDownloader.BASE_URL
            )
        } catch (e : Exception) {
            throw e
        }

        //TODO check if response body is valid

        val remoteData =  RemoteData(UserDataParser.parseData(response.body!!))

        //create parallel network request to optimize download speed
        coroutineScope {
            val list: MutableList<Deferred<Unit>> = ArrayList()
            for (currentUser in remoteData.users) {
                try {
                    val job = async {
                        val resp = remoteDataDownloader.executeRequest(currentUser.imageUrl)

                        //TODO check if response body is valid
                         currentUser.profilePicture = BitmapParser.parseData(resp.body!!)
                    }
                    list.add(job)
                } catch (e: Exception) {
                    throw e
                }
            }
            list.forEach {job ->
                job.await()
            }
        }


        return remoteData

    }

}