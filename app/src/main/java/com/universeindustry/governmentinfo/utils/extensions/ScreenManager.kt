package com.universeindustry.governmentinfo.utils.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.core.content.ContextCompat

object ScreenManager {
    fun Float.toPx(context: Context) = (this * context.resources.displayMetrics.scaledDensity + 0.5F)
    fun Float.toDP(context: Context) : Int {
        val toDP = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, context.resources.displayMetrics).toInt()
        return toDP
    }

    fun dpToPixels(dp: Int, context : Context): Int {
        val scale = context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }
    fun pixelsToDp(pixels: Int, context : Context): Int {
        val scale = context.resources.displayMetrics.density
        return (pixels / scale + 0.5f).toInt()
    }

    fun imageResize(context : Context, image : Int, width : Int, height : Int) : Drawable {
        val drawable = ContextCompat.getDrawable(context, image)
        val bitmap = (drawable as BitmapDrawable).bitmap
        return BitmapDrawable(context.resources, Bitmap.createScaledBitmap(bitmap, width, height, true))
    }
}