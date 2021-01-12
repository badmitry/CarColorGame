package com.badmitry.carcolorgame.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface IColorGameView : MvpView {
    fun startGame()
    fun sound(int: Int)
    fun createBlob(colorInt: Int, xCord: Float, yCord: Float)
}
