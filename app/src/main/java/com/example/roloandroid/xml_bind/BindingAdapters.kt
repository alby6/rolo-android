package com.example.roloandroid.xml_bind

import android.graphics.Bitmap
import android.graphics.Color
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.example.roloandroid.R
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
fun evaluateFavorite(iv : AppCompatImageView, isStarred : Boolean) {

    if (isStarred) {
        iv.setTag(R.string.iv_tag, true)
        iv.setBackgroundResource(R.drawable.ic_orange_circle)
        //btn.setBackgroundColor(Color.YELLOW)
    } else {
        iv.setTag(R.string.iv_tag, false)
        iv.setBackgroundResource(R.drawable.ic_gray_circle)
        //btn.setBackgroundColor(Color.RED)
    }

}

