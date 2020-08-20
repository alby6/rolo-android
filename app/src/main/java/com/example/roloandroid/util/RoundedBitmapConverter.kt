package com.example.roloandroid.util

import android.content.Context
import android.graphics.Bitmap
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory


class RoundedBitmapConverter {

    companion object {
        fun roundBmp(bmp : Bitmap, ctx : Context) : RoundedBitmapDrawable {
            val dr = RoundedBitmapDrawableFactory.create(ctx.resources, bmp)
            dr.setCornerRadius(bmp.getWidth().coerceAtLeast(bmp.height) / 2.0f)
            return dr
        }

    }



}


