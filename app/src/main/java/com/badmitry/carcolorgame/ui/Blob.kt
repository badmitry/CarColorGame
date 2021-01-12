package com.badmitry.carcolorgame.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import com.badmitry.carcolorgame.R
import java.util.*


class Blob(private val context: Context, val colorCode: Int, val xCord: Float, val yCord: Float) {
    var size // размер
            = 0f
    private var bitmap // картинка
            : Bitmap? = null
    private val radius = 4 // радиус

    init {
        size = radius * 2.toFloat()
        setBitmap()
    }

    private fun setBitmap() {
        var bitmapId = 0
            when (colorCode) {
                0 -> bitmapId = R.drawable.blackb
                1 -> bitmapId = R.drawable.blueb
                2 -> bitmapId = R.drawable.brownb
                3 -> bitmapId = R.drawable.greenb
                4 -> bitmapId = R.drawable.greyb
                5 -> bitmapId = R.drawable.lblueb
                6 -> bitmapId = R.drawable.orangeb
                7 -> bitmapId = R.drawable.pinkb
                8 -> bitmapId = R.drawable.purpleb
                9 -> bitmapId = R.drawable.redb
                10 -> bitmapId = R.drawable.whiteb
                11 -> bitmapId = R.drawable.yellowb
                else -> {
                    bitmapId = R.drawable.blackb
                }
            }
        val cBitmap = BitmapFactory.decodeResource(context.resources, bitmapId)
        bitmap = Bitmap.createScaledBitmap(
            cBitmap, (size * GameView.unitW).toInt(), (size * GameView.unitH).toInt(), false
        )
        cBitmap.recycle()
    }

    fun drow(paint: Paint?, canvas: Canvas?) { // рисуем картинку
        bitmap?.let {
            canvas?.drawBitmap(it, xCord * GameView.unitW, yCord * GameView.unitH, paint)
        }
    }
}