package com.badmitry.carcolorgame.mvp.presenter

import com.badmitry.carcolorgame.mvp.model.ISettings
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
    lateinit var settingsRepo: ISettings

    @Inject
    lateinit var uiSchedulers: Scheduler

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        takeChooseVoice()
    }

    fun takeChooseVoice() {
        settingsRepo.takeChooseVoice().observeOn(uiSchedulers).subscribe{it ->
            viewState.setChoseVoice(it)
        }
    }

    fun saveChoseVoice(code: Int) {
        settingsRepo.saveChooseVoice(code)
    }

    fun backPressed(): Boolean {
        router.backTo(Screens.ChooseGameScreen())
        return true
    }
}