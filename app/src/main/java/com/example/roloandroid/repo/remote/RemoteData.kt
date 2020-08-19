package com.example.roloandroid.repo.remote

import com.example.roloandroid.data.User


data class RemoteData (
    //wrapper class in case there are future specs required, e.g. 'version number', 'time/date' etc
    val users : List<User>
)