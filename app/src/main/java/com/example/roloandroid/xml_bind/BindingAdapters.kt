package com.example.roloandroid.xml_bind

import android.graphics.Bitmap
import android.graphics.Color
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.roloandroid.util.RoundedBitmapConverter
import com.example.roloandroid.util.Util
import kotlinx.coroutines.withContext


@BindingAdapter("bindRoundBmp")
fun bindRoundBmp(iv : ImageView, bmp : Bitmap?) {
    bmp?.let { unwrappedBmp ->
        val rb = RoundedBitmapConverter.roundBmp(unwrappedBmp, iv.context)
        iv.setImageDrawable(rb)
    }
}

@BindingAdapter("evaluateFavorite")
fun evaluateFavorite(btn : Button, isStarred : Boolean) {
    if (isStarred) {
        //iv.
    } else {
        //iv.setImageBitmap()
    }

}

