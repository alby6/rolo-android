package com.example.roloandroid.repo.remote

import javax.inject.Inject


class RemoteDataSource @Inject constructor(
    private val remoteDataDownloader: RemoteDataDownloader
) {
    fun executeRequest() : RemoteData? {
        val response = try {
            remoteDataDownloader.executeRequest()
        } catch (e : Exception) {
            throw e
        }

        //check if response body is valid
        return UserDataParser.parseData(
            response.body!!
        )
    }

}