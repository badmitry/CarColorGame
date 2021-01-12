package com.badmitry.carcolorgame.mvp.presenter

import com.badmitry.carcolorgame.mvp.view.ISplashView
import com.badmitry.carcolorgame.navigation.Screens
import com.badmitry.carcolorgame.ui.App
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class SplashPresenter : MvpPresenter<ISplashView>() {
    @Inject
    lateinit var router: Router

    @Inject
    lateinit var uiSchedulers: Scheduler

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.component.inject(this)
        viewState.playSound()
    }

    fun startMainActivity() {
        router.navigateTo(Screens.MainActivityScreen())
        viewState.finishSplash()
    }
}