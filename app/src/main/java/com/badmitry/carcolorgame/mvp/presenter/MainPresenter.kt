package com.badmitry.carcolorgame.mvp.presenter

import com.badmitry.carcolorgame.mvp.view.IMainView
import com.badmitry.carcolorgame.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter(): MvpPresenter<IMainView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.ChooseGameScreen())
    }

    fun backClick() {
        router.exit()
    }

}