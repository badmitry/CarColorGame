package com.badmitry.futurespositions.di.modules

import com.badmitry.carcolorgame.mvp.model.ISettings
import com.badmitry.carcolorgame.ui.App
import com.badmitry.carcolorgame.ui.SettingsRepo
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

@Module
class AppModule(private val app: App) {

    @Provides
    fun app() = app

    @Provides
    fun getUiSchelduler() = AndroidSchedulers.mainThread()

    @Provides
    fun getSettingsSaver(): ISettings = SettingsRepo(app)
}