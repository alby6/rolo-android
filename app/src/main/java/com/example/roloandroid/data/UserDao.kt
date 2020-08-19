package com.example.roloandroid.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query


@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll() : List<User>

}