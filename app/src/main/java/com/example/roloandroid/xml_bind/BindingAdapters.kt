package com.example.roloandroid.xml_bind

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter


@BindingAdapter("bindBmp")
fun bindBmp(iv : ImageView, bmp : Bitmap?) {
    bmp?.let {unwrappeBmp ->
        iv.setImageBitmap(unwrappeBmp)
    }
}
