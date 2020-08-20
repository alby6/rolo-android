package com.example.roloandroid.xml_bind

import android.graphics.Bitmap
import android.graphics.Color
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.roloandroid.util.Util



@BindingAdapter("bindBmp")
fun bindBmp(iv : ImageView, bmp : Bitmap?) {
    bmp?.let { unwrappedBmp ->
        iv.setImageBitmap(unwrappedBmp)
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

