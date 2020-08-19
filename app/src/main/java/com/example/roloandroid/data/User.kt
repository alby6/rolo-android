package com.example.roloandroid.data

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class User(
    @PrimaryKey val uid : Int,
    @ColumnInfo(name = "first_name") val firstName : String,
    @ColumnInfo(name = "last_name") val lastName : String,
    @ColumnInfo(name = "email") val email : String,
    @ColumnInfo(name = "is_favorite") val isFavorite : Boolean,
    @ColumnInfo(name = "profile_picture") val profilePicture : String?

    val fullName: String = "$firstName $lastName"
)