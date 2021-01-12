package com.badmitry.carcolorgame.mvp.model

import io.reactivex.rxjava3.core.Single

interface ISettings {
    fun takeChooseVoice(): Single<Int>
    fun saveChooseVoice(choose: Int)
}