package com.example.roloandroid.data

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")

data class User(
    @PrimaryKey val uid : Int,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "email") val email : String,
    @ColumnInfo(name = "is_favorite") val isFavorite : Boolean,
    @ColumnInfo(name = "profile_picture") val profilePicture : String?,

    @Embedded
    val company : Company
)