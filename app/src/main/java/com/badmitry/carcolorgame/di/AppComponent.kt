package com.badmitry.carcolorgame.di

import com.badmitry.carcolorgame.mvp.presenter.*
import com.badmitry.carcolorgame.ui.activity.MainActivity
import com.badmitry.carcolorgame.ui.activity.SplashActivity
import com.badmitry.futurespositions.di.modules.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NavigationModule::class
    ]
)
interface AppComponent {
    fun inject(splashActivity: SplashActivity)
    fun inject(splashPresenter: SplashPresenter)
    fun inject(mainPresenter: MainPresenter)
    fun inject(mainActivity: MainActivity)
    fun inject(chooseGamePresenter: ChooseGamePresenter)
    fun inject(colorGamePresenter: ColorGamePresenter)
    fun inject(settingsGamePresenter: SettingsGamePresenter)
}