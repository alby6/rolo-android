package com.example.roloandroid.xml_bind

import android.graphics.Bitmap
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.roloandroid.util.Util



@BindingAdapter("decodeBindBmp")
fun decodeBindBmp(iv : ImageView, bmp : Bitmap?) {
    /*
    base64BitmapStr?.let {bmp ->
        iv.setImageBitmap(Util.decode64Bmp(bmp))
    }

     */
}

@BindingAdapter("evaluateFavorite")
fun evaluateFavorite(btn : Button, isStarred : Boolean) {
    if (isStarred) {
        //iv.
    } else {
        //iv.setImageBitmap()
    }

}

