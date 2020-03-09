package jp.digital.future.wearSupporter.di

import android.app.Application
import jp.digital.future.wearSupporter.AppLifecycleCallbacks
import timber.log.Timber

class DebugAppLifecycleCallbacks : AppLifecycleCallbacks {

    override fun onCreate(application: Application) {
        Timber.plant(Timber.DebugTree())
    }

    override fun onTerminate(application: Application) {

    }
}
