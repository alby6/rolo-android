package com.example.roloandroid

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class FadePageTransformer : ViewPager2.PageTransformer {
    override fun transformPage(view: View, position: Float) {
        view.translationX = view.width * -position

        if (position <= -1.0f || position >= 1.0f) {
            view.alpha = 0.0f
            view.visibility = View.GONE;
        } else if (position == 0.0f) {
            view.alpha = 1.0f
            view.visibility = View.VISIBLE;
        } else {
            // position is between -1.0F & 0.0F OR 0.0F & 1.0F
            view.alpha = 1.0f - Math.abs(position)
            view.visibility = View.GONE;
        }
    }
}
