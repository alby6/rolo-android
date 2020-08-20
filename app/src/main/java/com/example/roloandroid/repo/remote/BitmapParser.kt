package com.example.roloandroid.repo.remote

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.roloandroid.util.Util
import okhttp3.ResponseBody


class BitmapParser {

    companion object {
        fun parseData(response : ResponseBody) : Bitmap {
            return BitmapFactory.decodeStream(response.byteStream())
        }
    }

}