package com.sardordev.translator.app

import android.app.Application
import com.sardordev.translator.data.database.DataBaseSaved
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App :  Application(){
    override fun onCreate() {
        super.onCreate()
        DataBaseSaved.init(this)
    }

}