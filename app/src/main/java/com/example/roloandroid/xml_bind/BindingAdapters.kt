package com.example.roloandroid.xml_bind

import android.graphics.Bitmap
import android.graphics.Color
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.roloandroid.R
import com.example.roloandroid.util.RoundedBitmapConverter
import com.example.roloandroid.util.Util
import com.google.android.material.internal.VisibilityAwareImageButton
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
        iv.setBackgroundResource(R.drawable.ic_gray_circle)
    } else {
        iv.setTag(R.string.iv_tag, false)
        iv.setBackgroundResource(R.drawable.ic_orange_circle)
    }
}

@BindingAdapter("evaluateMiniStar")
fun evaluateMiniStar(iv : AppCompatImageView, isStarred : Boolean) {
    if (isStarred) {
        iv.visibility = VISIBLE
    } else {
        iv.visibility = GONE
    }
}


@BindingAdapter("invertButtonColor")
fun invertButtonColor(btn : Button, boolean : Boolean) {
    //boolean is not used; BindingAdapter requires a non-null second parameter
    if (btn.tag == "blue_background") {
        btn.setBackgroundResource(R.drawable.ic_white_button_background)
        btn.tag = "white_background"
    } else {
        btn.setBackgroundResource(R.drawable.ic_blue_button_background)
        btn.tag = "blue_background"
    }
}
