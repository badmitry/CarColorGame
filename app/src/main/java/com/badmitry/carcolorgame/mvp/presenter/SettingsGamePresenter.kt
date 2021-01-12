package com.badmitry.carcolorgame.mvp.presenter

import com.badmitry.carcolorgame.mvp.model.ISaverSettings
import com.badmitry.carcolorgame.mvp.view.ISettingsGameView
import com.badmitry.carcolorgame.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class SettingsGamePresenter : MvpPresenter<ISettingsGameView>() {
    @Inject
    lateinit var router: Router

    @Inject
    lateinit var saverSettingsRepo: ISaverSettings

    @Inject
    lateinit var uiSchedulers: Scheduler

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        takeChooseVoice()
    }

    fun takeChooseVoice() {
        saverSettingsRepo.takeChooseVoice().observeOn(uiSchedulers).subscribe{ it ->
            viewState.setChoseVoice(it)
        }
    }

    fun saveChoseVoice(code: Int) {
        saverSettingsRepo.saveChooseVoice(code)
    }

    fun backPressed(): Boolean {
        router.backTo(Screens.ChooseGameScreen())
        return true
    }
}