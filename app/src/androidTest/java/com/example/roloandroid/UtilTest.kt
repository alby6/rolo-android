package com.example.roloandroid

import android.graphics.Bitmap
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.roloandroid.util.Util
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat

@RunWith(AndroidJUnit4::class)

class UtilTest {
    @Test
    fun testBitmap() {
        val testBitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888)
        val encodedBmp = Util.encode64Bmp(testBitmap)
        val decodedBmp = Util.decode64Bmp(encodedBmp)
        assertThat(testBitmap.width).isEqualTo(decodedBmp.width)
        assertThat(testBitmap.height).isEqualTo(decodedBmp.height)
        assertThat(testBitmap.colorSpace).isEqualTo(decodedBmp.colorSpace)
    }
}
