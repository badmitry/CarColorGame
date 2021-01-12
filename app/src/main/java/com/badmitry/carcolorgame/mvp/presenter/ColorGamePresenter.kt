package com.badmitry.carcolorgame.mvp.presenter

import com.badmitry.carcolorgame.mvp.view.IColorGameView
import com.badmitry.carcolorgame.navigation.Screens
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ColorGamePresenter(val codeVoice: Int?) : MvpPresenter<IColorGameView>() {
    @Inject
    lateinit var router: Router

    private val compositeDisposable = CompositeDisposable()

    fun backPressed(): Boolean {
        router.backTo(Screens.ChooseGameScreen())
        return true
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.startGame()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    fun touch(colorInt: Int, xCord: Float, yCord: Float) {
        viewState.sound(colorInt)
        viewState.createBlob(colorInt, xCord, yCord)
    }
}