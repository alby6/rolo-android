package com.example.roloandroid.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream


class Util {

    //convert bmp to base64 to save in room
    companion object {

        fun encode64Bmp(bmp : Bitmap) : String {
            val byteArrayOutputStream = ByteArrayOutputStream()
            bmp.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT)
        }

        fun decode64Bmp(base64Str : String) : Bitmap {
            val decodedBytes = Base64.decode(
                base64Str.substring(base64Str.indexOf(",")  + 1),
                Base64.DEFAULT
            )
            return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size);
        }

    }

}

