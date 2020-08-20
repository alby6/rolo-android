package com.example.roloandroid.data

import android.graphics.Bitmap
import androidx.room.*


@Entity(tableName = "user")

data class User(
    @PrimaryKey val uid : Int,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "email") val email : String,
    @ColumnInfo(name = "is_favorite") var isFavorite : Boolean,
    @ColumnInfo(name = "imageUrl") val imageUrl : String,
    @ColumnInfo(name = "profilePictureBase64") var profilePictureBase64 : String?,

    @Embedded
    val company : Company
) {
    @Ignore
    var profilePicture: Bitmap? = null

}