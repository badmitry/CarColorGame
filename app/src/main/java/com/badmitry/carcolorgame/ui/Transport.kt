package com.badmitry.carcolorgame.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import com.badmitry.carcolorgame.R
import java.util.*


class Transport(private val context: Context, val colorCode: Int) {
    var toDestroy = false
    var xCord // координаты
            = 0f
    var yCord = 0f
    var size // размер
            = 0f
    var speed // скорость
            = 0f
    private var bitmap // картинка
            : Bitmap? = null
    private val radius = 3 // радиус
    private val minSpeed = 0.05.toFloat() // минимальная скорость
    private val maxSpeed = 0.2.toFloat() // максимальная скорость

    init {
        val random = Random()
        yCord = (random.nextInt(GameView.maxY - 2 * radius) + 1).toFloat()
        size = radius * 2.toFloat()
        speed = minSpeed + (maxSpeed - minSpeed) * random.nextFloat()
        setBitmap()
    }

    private fun setBitmap() {
        var bitmapId = 0
        if (yCord > GameView.maxY / 4) {
            when (colorCode) {
                0 -> bitmapId = R.drawable.black
                1 -> bitmapId = R.drawable.blue
                2 -> bitmapId = R.drawable.brown
                3 -> bitmapId = R.drawable.green
                4 -> bitmapId = R.drawable.grey
                5 -> bitmapId = R.drawable.lblue
                6 -> bitmapId = R.drawable.orange
                7 -> bitmapId = R.drawable.pink
                8 -> bitmapId = R.drawable.purple
                9 -> bitmapId = R.drawable.red
                10 -> bitmapId = R.drawable.white
                11 -> bitmapId = R.drawable.yellow
                else -> {
                    bitmapId = R.drawable.black
                }
            }
        } else {
            when (colorCode) {
                0 -> bitmapId = R.drawable.blackh
                1 -> bitmapId = R.drawable.blueh
                2 -> bitmapId = R.drawable.brownh
                3 -> bitmapId = R.drawable.greenh
                4 -> bitmapId = R.drawable.greyh
                5 -> bitmapId = R.drawable.lblueh
                6 -> bitmapId = R.drawable.orangeh
                7 -> bitmapId = R.drawable.pinkh
                8 -> bitmapId = R.drawable.purpleh
                9 -> bitmapId = R.drawable.redh
                10 -> bitmapId = R.drawable.whiteh
                11 -> bitmapId = R.drawable.yellowh
                else -> {
                    bitmapId = R.drawable.blackh
                }
            }
        }
        val cBitmap = BitmapFactory.decodeResource(context.resources, bitmapId)
        bitmap = Bitmap.createScaledBitmap(
            cBitmap, (size * GameView.unitW).toInt(), (size * GameView.unitH).toInt(), false
        )
        cBitmap.recycle()
    }

    fun update() {
        xCord += speed
    }

    fun drow(paint: Paint?, canvas: Canvas?) { // рисуем картинку
        bitmap?.let {
            canvas?.drawBitmap(it, xCord * GameView.unitW, yCord * GameView.unitH, paint)
        }
    }

    fun isCollision(touchX: Float, touchY: Float): Boolean {
        return (xCord < touchX && xCord + size > touchX && yCord < touchY && yCord + size > touchY)
    }

    fun toDestroy(): Boolean {
        return (xCord >= GameView.unitW || toDestroy)
    }
}