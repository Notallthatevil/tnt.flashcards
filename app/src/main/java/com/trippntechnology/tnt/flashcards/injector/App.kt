package com.trippntechnology.tnt.flashcards.injector

import android.app.Application
import com.trippntechnology.tnt.flashcards.BuildConfig
import com.trippntechnology.tnt.flashcards.util.log.DebugTree
import com.trippntechnology.tnt.flashcards.util.log.ReleaseTree
import timber.log.Timber

class App:Application() {

    init {
        Injector.init(this)
    }

    override fun onCreate() {
        super.onCreate()
        Injector.get().inject(this)

        setupLogging()

    }

    private fun setupLogging() {
        // Always register Crashltyics (even if CrashlyticsTree is not planted)
        //        Fabric.with(this, new Crashlytics());

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }
}