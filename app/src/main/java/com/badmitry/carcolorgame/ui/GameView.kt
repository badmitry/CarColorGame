package com.badmitry.carcolorgame.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.view.MotionEvent
import android.view.SurfaceView
import com.badmitry.carcolorgame.mvp.presenter.ColorGamePresenter
import java.lang.Exception


class GameView(context: Context, private val presenter: ColorGamePresenter) : SurfaceView(context),
    Runnable {
    companion object {
        var maxX = 20 // размер по горизонтали
        var maxY = 28 // размер по вертикали
        var unitW = 0f // пикселей в юните по горизонтали
        var unitH = 0f // пикселей в юните по вертикали
    }

    private var firstTime = true
    var gameThread: Thread? = null
    private var paint: Paint = Paint()
    private var canvas: Canvas? = null
    private var surfaceHolder = holder
    private val transports: ArrayList<Transport> = ArrayList()
    private val CARS_INTERVAL = 50
    private val CAR_COUNTER = 5
    private var currentTime = 0
    private var colorCounter = 0
    private var transportsColorCode = 0
    private var running = false
    private var blob: Blob? = null
    private var blobCounter = 0

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (blob == null) {
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    event?.x?.let {
                        val newX = it / unitW
                        val newY = event.y / unitH
                        try {
                            for (transport in transports) {
                                if (transport.isCollision(newX, newY)) {
                                    presenter.touch(
                                        transport.colorCode,
                                        transport.xCord,
                                        transport.yCord
                                    )
                                    transport.toDestroy = true
                                    break
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }

                    }
                }
                else -> {
                }
            }
        }
        return true
    }

    fun onResumeSurfaceView() {
        running = true
        gameThread = Thread(this)
        gameThread?.start()
    }

    fun onPauseSurfaceView() {
        var retry = true
        running = false
        while (retry) {
            try {
                gameThread?.join()
                retry = false
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    override fun run() {
        while (running) {
            update()
            draw()
            checkIfBlobDestroy()
            checkIfNewAsteroid()
            destroyCar()
            control()
        }
    }

    private fun destroyCar() {
        for (car in transports) {
            if (car.toDestroy()) {
                transports.remove(car)
                destroyCar()
                break
            }
        }
    }

    private fun update() {
        if (!firstTime) {
            for (car in transports) {
                car.update()
            }
        }
    }

    private fun draw() {
        surfaceHolder?.let {
            if (it.surface.isValid) {  //проверяем валидный ли surface
                if (firstTime) { // инициализация при первом запуске
                    firstTime = false
                    unitW =
                        it.surfaceFrame.width() / maxX.toFloat() // вычисляем число пикселей в юните
                    unitH = it.surfaceFrame.height() / maxY.toFloat()
                }
                canvas = it.lockCanvas()
                canvas?.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
                try {
                    for (car in transports) {
                        car.drow(paint, canvas)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                blob?.drow(paint, canvas)
                it.unlockCanvasAndPost(canvas)
            }
        }

    }

    private fun control() { // пауза на 17 миллисекунд
        try {
            Thread.sleep(17)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    private fun checkIfBlobDestroy() {
        blob?.let {
            blobCounter++
            if (blobCounter == 15) {
                blobCounter = 0
                blob = null
            }
        }
    }

    private fun checkIfNewAsteroid() {
        if (currentTime >= CARS_INTERVAL) {
            if (colorCounter == CAR_COUNTER) {
                colorCounter = 0
                transportsColorCode++
            }
            if (transportsColorCode > 11) {
                transportsColorCode = 0
                colorCounter = 1
            }
            colorCounter++
            val car = Transport(context, transportsColorCode)
            transports.add(car)
            currentTime = 0
        } else {
            currentTime++
        }
    }

    fun createBlob(colorInt: Int, xCord: Float, yCord: Float) {
        blob = Blob(context, colorInt, xCord, yCord)
        blobCounter = 0
    }
}