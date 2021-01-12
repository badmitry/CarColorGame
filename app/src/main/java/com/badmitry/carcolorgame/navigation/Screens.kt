package com.badmitry.carcolorgame.navigation

import android.content.Context
import android.content.Intent
import com.badmitry.carcolorgame.ui.activity.MainActivity
import com.badmitry.carcolorgame.ui.fragments.ChooseGameFragment
import com.badmitry.carcolorgame.ui.fragments.ColorGameFragment
import com.badmitry.carcolorgame.ui.fragments.SettingsGameFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class MainActivityScreen : SupportAppScreen() {
        override fun getActivityIntent(context: Context?) =
            Intent(context, MainActivity::class.java)
    }

    class ChooseGameScreen : SupportAppScreen() {
        override fun getFragment() = ChooseGameFragment.newInstance()
    }

    class ColorGame(private val codeVoice: Int) : SupportAppScreen() {
        override fun getFragment() = ColorGameFragment.newInstance(codeVoice)
    }

    class SettingsGame : SupportAppScreen() {
        override fun getFragment() = SettingsGameFragment.newInstance()
    }
}