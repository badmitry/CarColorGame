package com.badmitry.carcolorgame.mvp.presenter

import com.badmitry.carcolorgame.mvp.model.ISaverSettings
import com.badmitry.carcolorgame.mvp.view.IChooseGameView
import com.badmitry.carcolorgame.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ChooseGamePresenter : MvpPresenter<IChooseGameView>() {
    @Inject
    lateinit var router: Router

    @Inject
    lateinit var saverSettingsRepo: ISaverSettings

    @Inject
    lateinit var uiSchedulers: Scheduler

    fun startColorGame() {
        saverSettingsRepo.takeChooseVoice().observeOn(uiSchedulers).subscribe{ it ->
            router.navigateTo(Screens.ColorGame(it))
        }
    }

    fun openSettings() {
        router.navigateTo(Screens.SettingsGame())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}