package com.badmitry.carcolorgame.ui.activity

import android.media.MediaPlayer
import android.os.Bundle
import com.badmitry.carcolorgame.R
import com.badmitry.carcolorgame.mvp.presenter.SplashPresenter
import com.badmitry.carcolorgame.mvp.view.ISplashView
import com.badmitry.carcolorgame.ui.App
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class SplashActivity : MvpAppCompatActivity(), ISplashView {
    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val presenter by moxyPresenter {
        SplashPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.component.inject(this)
    }

    override fun onResume() {
        super.onResume()
        val navigator = SupportAppNavigator(this, 0)
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun finishSplash() {
        this.finish()
    }

    override fun playSound() {
        val mp = MediaPlayer.create(applicationContext, R.raw.quiet)
        mp.setOnCompletionListener {
            presenter.startMainActivity()
        }
        mp.start()
    }
}