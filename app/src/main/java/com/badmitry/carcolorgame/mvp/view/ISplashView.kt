package com.badmitry.carcolorgame.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ISplashView: MvpView {
    fun finishSplash()
    fun playSound()
}
