package com.badmitry.carcolorgame.ui

import android.content.Context
import android.content.SharedPreferences
import com.badmitry.carcolorgame.mvp.model.ISettings
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


class SettingsRepo(context: Context): ISettings {
    private var sharedPrefs: SharedPreferences? = null

    val PREFS = "prefs"
    val KEY_VOICE = "nameKey"

    init {
        sharedPrefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    override fun takeChooseVoice(): Single<Int> = Single.create<Int> { emitter ->
        sharedPrefs?.let{
            if (it.contains(KEY_VOICE)) {
                emitter.onSuccess(it.getInt(KEY_VOICE, 0))
            }
        }?:emitter.onSuccess(0)
    }.subscribeOn(Schedulers.io())

    override fun saveChooseVoice(choose: Int) {
        sharedPrefs?.let{
            val editor = it.edit()
            editor.putInt(KEY_VOICE, choose)
            editor.apply()
        }
    }
}